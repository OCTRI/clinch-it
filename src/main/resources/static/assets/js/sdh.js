(function() {
	var app = new Vue({
		el: '#contents',
		data: {
			fields: [{key:'domain', sortable:true}, {key:'date_last_assessed', sortable:true}, {key:'clinician_priority', sortable:true}, {key:'patient_readiness', sortable:true}],
			filters: {
			      domain: '',
			      date_last_assessed: '',
			      clinician_priority: '',
			      patient_readiness: ''
			    },
	        items: [
	            { domain: 'Food Security', date_last_assessed: '01/01/2018', clinician_priority: 'High', patient_readiness: 'Not Ready' },
	            { domain: 'Transportation', date_last_assessed: '01/01/2019', clinician_priority: 'Low', patient_readiness: 'Motivated'},
	            { domain: 'Housing Instability', date_last_assessed: '10/11/2018', clinician_priority: 'Medium', patient_readiness: 'Ready' },
	            { domain: 'Utilities', date_last_assessed: '02/01/2019', clinician_priority: 'High', patient_readiness: 'Ready' },
	            { domain: 'Interpersonal Violence', date_last_assessed: '12/01/2018', clinician_priority: 'Medium', patient_readiness: 'Motivated' },
	            { domain: 'Education', date_last_assessed: '04/04/2018', clinician_priority: 'Medium', patient_readiness: 'Not Ready' },
	            { domain: 'Financial Strain', date_last_assessed: '03/17/2012', clinician_priority: 'Low', patient_readiness: 'Ready' }
	          ]
		},
		methods: {
			_priorityVariant (priority) {
				if (priority == 'High') {
					return 'danger';
				} else if (priority == 'Medium') {
					return 'warning';
				} else if (priority == 'Low') {
					return 'success';
				}
		    },			
			_readinessVariant (readiness) {
				if (readiness == 'Not Ready') {
					return 'danger';
				} else if (readiness == 'Motivated') {
					return 'warning';
				} else if (readiness == 'Ready') {
					return 'success';
				}
		    }			
		},
		computed: {
		    filtered () {
		        const filtered = this.items.filter(item => {
		          return Object.keys(this.filters).every(key =>
		              String(item[key].toLowerCase()).includes(this.filters[key].toLowerCase()));
		        });
		        return filtered.length > 0 ? filtered : [{
		          domain: '',
			      date_last_assessed: '',
		          clinician_priority: '',
		          patient_readiness: ''
		        }];
		    },
		    emphasized () {
		    	const { filtered, _priorityVariant, _readinessVariant } = this;
		    	const emphasized = this.filtered.map(item => {
		    		let tmp=item;
		    		let clinicianPriorityEmphasis = _priorityVariant(item.clinician_priority);
		    		let patientReadinessEmphasis = _readinessVariant(item.patient_readiness);
		    		tmp._cellVariants = {'clinician_priority': clinicianPriorityEmphasis, 'patient_readiness': patientReadinessEmphasis};
		    		return tmp;
		    	});
		    	return emphasized;
		    }
		}
	});
})();
