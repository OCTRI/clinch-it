(function() {
	new Vue({
		el: '#contents',
		data: {
			fields: [ {key:'show_details', label:' ', headerTitle: 'Show Details', sortable:false}, {key:'domain', sortable:true}, {key:'date_last_reviewed', sortable:true}, {key:'clinician_priority', sortable:true}, {key:'patient_readiness', sortable:true}, {key:'edit', label:' ', headerTitle: 'Edit', sortable:false}],
			filters: {
			      domain: '',
			      date_last_reviewed: '',
			      clinician_priority: '',
			      patient_readiness: ''
			    },
	        domainOptions: [],
	        reviews: [],
	        modalEditRow: { title: '', id: '', domain:'', prioritySelected: '', readinessSelected: '', priorityOptions: [], readinessOptions: [] }
		},
		mounted() {
			const patient = this.$el.getAttribute('data-patient-id');
			const contextPath = this.$el.getAttribute('data-context-path');
			$.ajax({
				url: contextPath + '/api/sdh_domain/',
				contentType: 'application/json',
				success: data => {
					this.domainOptions = data._embedded.sdhDomains;
				}
			});
			$.ajax({
				url: contextPath + '/api/clinician_priority/',
				contentType: 'application/json',
				success: data => {
					this.modalEditRow.priorityOptions = data._embedded.clinicianPriorities.map(it => {
						return {value: it.id, text: it.description}
					});
				}
			});
			$.ajax({
				url: contextPath + '/api/patient_readiness/',
				contentType: 'application/json',
				success: data => {
					this.modalEditRow.readinessOptions = data._embedded.patientReadinesses.map(it => {
						return {value: it.id, text: it.description}
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
		    _isEdit(field) {
		    	return field.key === 'edit';
		    },
		    _formatDate(dateString) {
		    	return dateString.replace(/ \d\d:\d\d:\d\d\.\d/, '');
		    },
		    edit(item, index, target) {
		    	this.modalEditRow.domain = this.domainOptions.filter(it => it.description === item.domain)[0].id;
		    	if (item.id === "") {
		    		this.modalEditRow.title = 'New Clinical Review';
		    	} else {
		    		this.modalEditRow.title = 'Edit Clinical Review';
		    		this.modalEditRow.id = item.id;
		    	      this.modalEditRow.prioritySelected = this.modalEditRow.priorityOptions.filter(it => it.text === item.clinician_priority)[0].value;
			        this.modalEditRow.readinessSelected = this.modalEditRow.readinessOptions.filter(it => it.text === item.patient_readiness)[0].value;
		    	}
				this.$root.$emit('bv::show::modal', 'modalEditRow', target);
		    },
		    resetModal() {
		        this.modalEditRow.title = '';
		        this.modalEditRow.id = '';
		        this.modalEditRow.domain = '';
		        this.modalEditRow.prioritySelected = '';
		        this.modalEditRow.readinessSelected = '';
		    },
		    handleOk(evt) {
		        // Prevent modal from closing so we can validate
		        evt.preventDefault();
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
				let {url, method, data} = '';
				if (clinicianReviewId) {
					url = contextPath + '/api/clinician_review/' + clinicianReviewId;
					method = 'PATCH';
					data = JSON.stringify({
						clinicianPriority: contextPath + '/api/clinician_priority/' + this.modalEditRow.prioritySelected,
						patientReadiness: contextPath + '/api/patient_readiness/' + this.modalEditRow.readinessSelected
					});
				} else {
					url = contextPath + '/api/clinician_review/';
					method = 'POST';
					data = JSON.stringify({
						patient: contextPath + '/api/patient/' + patient,
						domain: contextPath + '/api/sdh_domain/' + this.modalEditRow.domain,
						clinicianPriority: contextPath + '/api/clinician_priority/' + this.modalEditRow.prioritySelected,
						patientReadiness: contextPath + '/api/patient_readiness/' + this.modalEditRow.readinessSelected
					});					
				}
				
				$.ajax({
					url: url,
					method: method,
					data: data,
					contentType: 'application/json',
					success: data => {
						// TODO: Better way to reload without getting all reviews again
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
						return review.domain === domain.description;
					});
					if (reviewForDomain.length === 0) {
						return {id: '', domain: domain.description, date_last_reviewed: 'NA', clinician_priority:'None', patient_readiness: 'None'};
					} else {
						let review = reviewForDomain[0]; // Unique constraint prevents more than one
						return {id: review.id, domain: domain.description, date_last_reviewed: this._formatDate(review.updatedAt), clinician_priority: review.clinicianPriority, patient_readiness: review.patientReadiness};						
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
			      date_last_reviewed: '',
		          clinician_priority: '',
		          patient_readiness: ''
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
