class ModalEditRow {
	
	constructor() {
		this.title = '';
		this.id = null;
		this.domain = '';
		this.prioritySelected = '';
		this.readinessSelected = '';
		this.referred = '';
		this.referralComplete = '';
	}
	
	setFields(title, id, domain, prioritySelected, readinessSelected, referred, referralComplete) {
		this.title = title;
		this.id = id;
		this.domain = domain;
		this.prioritySelected = prioritySelected;
		this.readinessSelected = readinessSelected;
		this.referred = referred;
		this.referralComplete = referralComplete;
	}
};

(function() {	
	new Vue({
		el: '#contents',
		data: {
			fields: [ {key:'show_details', label:' ', headerTitle: 'Show Details', sortable:false}, {key:'domain', sortable:true}, {key:'last_reviewed', sortable:true}, {key:'clinician_priority', sortable:true}, {key:'patient_readiness', sortable:true}, {key:'referred', sortable:true}, {key:'referral_complete', sortable:true}, {key:'edit', label:' ', headerTitle: 'Edit', sortable:false}],
			filters: {
			      domain: '',
			      last_reviewed: '',
			      clinician_priority: '',
			      patient_readiness: '',
			      referred: '',
			      referral_complete: ''
			    },
	        domainOptions: [],
	        priorityOptions: [],
	        readinessOptions: [],
	        yesNoOptions: [{value: true, text:'Y'}, {value: false, text:'N'}],
	        reviews: [],
	        modalEditRow: new ModalEditRow()
		},
		mounted() {
			const patient = this.$el.getAttribute('data-patient-id');
			const contextPath = this.$el.getAttribute('data-context-path');
			$.ajax({
				url: contextPath + '/api/sdh_domain/',
				contentType: 'application/json',
				success: data => {
					this.domainOptions = data._embedded.sdhDomains.map(it => {
						return {value: it._links.self.href, text: it.description};
					});
				}
			});
			$.ajax({
				url: contextPath + '/api/clinician_priority/',
				contentType: 'application/json',
				success: data => {
					this.priorityOptions = data._embedded.clinicianPriorities.map(it => {
						return {value: it._links.self.href, text: it.description};
					});
				}
			});
			$.ajax({
				url: contextPath + '/api/patient_readiness/',
				contentType: 'application/json',
				success: data => {
					this.readinessOptions = data._embedded.patientReadinesses.map(it => {
						return {value: it._links.self.href, text: it.description}
					});
				}
			});
			$.ajax({
				url: contextPath + '/api/clinician_review/search/findByPatientId?id=' + patient,
				contentType: 'application/json',
				success: data => {
					this.reviews = data._embedded.clinicianReviews;
				}
			});
		},
		methods: {
			_priorityVariant (priority) {
				if (priority === 'High') {
					return 'danger';
				} else if (priority === 'Medium') {
					return 'warning';
				} else if (priority === 'Low') {
					return 'success';
				} else {
					return '';
				}
		    },			
			_readinessVariant (readiness) {
				if (readiness === 'Not Ready') {
					return 'danger';
				} else if (readiness === 'Motivated') {
					return 'warning';
				} else if (readiness === 'Ready') {
					return 'success';
				} else {
					return '';
				}
		    },
		    _isShowDetails(field) {
		    	return field.key === 'show_details';
		    },
		    _isUnfiltered(field) {
		    	return field.key === 'edit' || field.key === 'referred' || field.key === 'referral_complete';
		    },
		    _formatDate(dateString) {
		    	return dateString.replace(/ \d\d:\d\d:\d\d\.\d/, '');
		    },
		    _selectValue(options, textTarget) {
		    	// Find the select item by text and return the value
		    	return options.filter(it => it.text === textTarget)[0].value;
		    },
		    _selectText(options, valueTarget) {
		    	// Find the select item by value and return the text
		    	return options.filter(it => it.value === valueTarget)[0].text;
		    },
		    edit(item, index, target) {
		    	const domain = this._selectValue(this.domainOptions, item.domain);
	    		const referred = this._selectValue(this.yesNoOptions, item.referred);
	    		const referralComplete = this._selectValue(this.yesNoOptions, item.referral_complete);
		    	if (!item.id) {
		    		this.modalEditRow.setFields('New Clinician Review', null, domain, '', '', referred, referralComplete);
		    	} else {
		    		const prioritySelected = this._selectValue(this.priorityOptions, item.clinician_priority);
		    		const readinessSelected = this._selectValue(this.readinessOptions, item.patient_readiness);
		    		this.modalEditRow.setFields('Edit Clinical Review', item.id, domain, prioritySelected, readinessSelected, referred, referralComplete);
		    	}
				this.$root.$emit('bv::show::modal', 'modalEditRow', target);
		    },
		    resetModal() {
		    	return new ModalEditRow();
		    },
		    handleOk(evt) {
		        // Validate the inputs
		        if (!this.modalEditRow.prioritySelected || !this.modalEditRow.readinessSelected) {
		          alert('Please enter values');
		        } else {
		          this.handleSubmit();
		        }
		    },
		    handleSubmit() {
				const patient = this.$el.getAttribute('data-patient-id');
				const contextPath = this.$el.getAttribute('data-context-path');
				let clinicianReviewId = this.modalEditRow.id;
				let url = '';
				let method = '';
				let obj = {
					clinicianPriority: this.modalEditRow.prioritySelected,
					patientReadiness: this.modalEditRow.readinessSelected,
					referred: this.modalEditRow.referred,
					referralComplete: this.modalEditRow.referralComplete
				};
				if (clinicianReviewId) {
					url = contextPath + '/api/clinician_review/' + clinicianReviewId;
					method = 'PATCH';
				} else {
					url = contextPath + '/api/clinician_review/';
					method = 'POST';
					obj.patient = contextPath + '/api/patient/' + patient;
					obj.domain = this.modalEditRow.domain;
				}
				
				$.ajax({
					url: url,
					method: method,
					data: JSON.stringify(obj),
					contentType: 'application/json',
					success: data => {
						// Get the reviews again. The response does not provide info in a convenient manner for updating only what changed.
						$.ajax({
							url: contextPath + '/api/clinician_review/search/findByPatientId?id=' + patient,
							contentType: 'application/json',
							success: data => {
								this.reviews = data._embedded.clinicianReviews;
							}
						});
					}
				});
								
		    	this.$nextTick(() => {
		            // Wrapped in $nextTick to ensure DOM is rendered before closing
		            this.$refs.modal.hide();
		        });
		    }
		},
		computed: {
			clinicianReviewSummary () {				
				return this.domainOptions.map( domain => {
					let reviewForDomain = this.reviews.filter( review => {
						return review.domain === domain.text;
					});
					if (reviewForDomain.length === 0) {
						return {id: '', domain: domain.text, last_reviewed: 'NA', clinician_priority:'None', patient_readiness: 'None', referred: this._selectText(this.yesNoOptions, false), referral_complete: this._selectText(this.yesNoOptions, false)};
					} else {
						let review = reviewForDomain[0]; // Unique constraint prevents more than one
						return {id: review.id, domain: domain.text, last_reviewed: this._formatDate(review.updatedAt), clinician_priority: review.clinicianPriority, patient_readiness: review.patientReadiness, referred: this._selectText(this.yesNoOptions, review.referred), referral_complete: this._selectText(this.yesNoOptions, review.referralComplete)};						
					}
				});
			},
		    filtered () {
		        const filtered = this.clinicianReviewSummary.filter(item => {
		          return Object.keys(this.filters).every(key =>
		              item[key].toLowerCase().startsWith(this.filters[key].toLowerCase()));
		        });
		        return filtered.length > 0 ? filtered : [{
		          domain: '',
			      last_reviewed: '',
		          clinician_priority: '',
		          patient_readiness: '',
		          referred: '',
		          referral_complete: ''
		        }];
		    },
		    emphasized () {
		    	const { filtered, _priorityVariant, _readinessVariant } = this;
		    	const emphasized = filtered.map(item => {
		    		let tmp = Object.assign({}, item);
		    		tmp._showDetails = false; // Trigger row details with everything closed
		    		tmp._cellVariants = {
		    			clinician_priority: _priorityVariant(item.clinician_priority), 
		    			patient_readiness: _readinessVariant(item.patient_readiness)
		    		};
		    		return tmp;
		    	});
		    	return emphasized;
		    }
		}
	});
})();
