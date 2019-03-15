(function() {
	new Vue({
		el: '#contents',
		data: {
			fields: [ {key:'show_details', label:' ', headerTitle: 'Show Details', sortable:false}, {key:'domain', sortable:true}, {key:'date_last_reviewed', sortable:true}, {key:'clinician_priority', sortable:true}, {key:'patient_readiness', sortable:true}],
			filters: {
			      domain: '',
			      date_last_reviewed: '',
			      clinician_priority: '',
			      patient_readiness: ''
			    },
	        domainOptions: [],
	        priorityOptions: [],
	        readinessOptions: [],
	        reviews: []
			    
		},
		mounted: function() {
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
					this.priorityOptions = data._embedded.clinicianPriorities;
				}
			});
			$.ajax({
				url: contextPath + '/api/patient_readiness/',
				contentType: 'application/json',
				success: data => {
					this.readinessOptions = data._embedded.patientReadiness;
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
		    _formatDate(dateString) {
		    	return dateString.replace(/ \d\d:\d\d:\d\d\.\d/, '');
		    }
		},
		computed: {
			clinicianReviewSummary () {				
				return this.domainOptions.map( domain => {
					let reviewForDomain = this.reviews.filter( review => {
						return review.domain === domain.description;
					});
					if (reviewForDomain.length === 0) {
						return {'domain': domain.description, 'date_last_reviewed': 'NA', 'clinician_priority':'None', 'patient_readiness': 'None'};
					} else {
						let review = reviewForDomain[0]; // Unique constraint prevents more than one
						return {'domain': domain.description, 'date_last_reviewed': this._formatDate(review.updatedAt), 'clinician_priority': review.clinicianPriority, 'patient_readiness': review.patientReadiness};						
					}
				});
			},
		    filtered () {
		        const filtered = this.clinicianReviewSummary.filter(item => {
		          return Object.keys(this.filters).every(key =>
		              item[key].toLowerCase().includes(this.filters[key].toLowerCase()));
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
		    			'clinician_priority': _priorityVariant(item.clinician_priority), 
		    			'patient_readiness': _readinessVariant(item.patient_readiness)
		    		};
		    		return tmp;
		    	});
		    	return emphasized;
		    }
		}
	});
})();
