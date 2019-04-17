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
	
	const patient = $('#contents').attr('data-patient-id');
	const contextPath = $('#contents').attr('data-context-path');
	
	new Vue({
		el: '#contents',
		template: `
		  <div>
		  <b-table show-empty head-variant="dark" :items="emphasized" :fields="fields">
		  <template slot="top-row" slot-scope="{ fields }">
		    <td v-for="field in fields" :key="field.key">
		      <span v-if="_isShowDetails(field)">Filter By:</span>
		      <span v-else-if="_isUnfiltered(field)"></span>
		      <input v-else v-model="filters[field.key]" :placeholder="field.label">
		    </td>
		  </template>
     	  <template slot="show_details" slot-scope="row">
        	<!-- As row.showDetails is one-way, we call the toggleDetails function on @change -->
        	<b-form-checkbox @change="rowDetails(row, $event.target)" v-model="row.detailsShowing">
        	<i class="fas fa-angle-right"></i>
        	</b-form-checkbox>
      	  </template>
      	  <template slot="edit" slot-scope="row">
      	  	<i class="far fa-edit" @click="edit(row.item, row.index, $event.target)"></i>
      	  </template>
		  <template slot="row-details" slot-scope="row">
		    <b-card class="small">
		  	<div>
  				<b-tabs v-model="tabIndex" content-class="mt-3">
    				<b-tab title="Historical Responses">
    					<div v-if="hasResponseForDomain">
    						<label>Response Date</label>
							<b-form-select v-model="selectedResponseDate" :options="responseDates"></b-form-select>
    						<hr>
 							<b-form-group v-for="question in selectedResponse.questions" :key="question.number">
								<label :for="question.number">{{question.text}}</label>
								<b-form-select disabled :id="question.number" v-model="question.answer" :options="question.answers" size="sm"></b-form-select>
							</b-form-group>
							<b-form-group class="wants-help-question">
								<label>Would you like help addressing this need?</label>
								<b-form-select disabled v-model="selectedResponse.wantsHelp" :options="yesNoLongOptions"></b-form-select>
   							</b-form-group>
							<b-form-group>
								<label>Comments</label>
								<div v-if="selectedResponseDate === currentResponse.createdAt">
									<b-alert :show="dismissCountDown" dismissible variant="success" @dismissed="dismissCountDown=0">									
										Comments successfully saved....
									</b-alert>
									<b-form @submit.stop.prevent="editComments">
										<b-form-group>
											<!-- Limitations with b-form-text-area require the model to be data and not a computed property. -->
											<b-form-textarea v-model="currentComments" rows="3"></b-form-textarea>
										</b-form-group>
										<b-button type="submit" variant="primary">Submit</b-button>
									</b-form>
								</div>
								<div v-else> 
									<b-form-textarea readonly v-model="selectedResponse.comments" rows="3"></b-form-textarea>
								</div>
							</b-form-group>
   						</div>
   						<div v-else>
   							None
   						</div>
    				</b-tab>
    				<b-tab title="New Questionnaire Response" >
						<b-form @submit.stop.prevent="validateResponse">
							<b-form-group v-for="question in newResponse.questions">
								<label :for="question.number">{{question.text}}</label>
								<b-form-select :id="question.number" v-model="question.answer" :options="question.answers" size="sm"></b-form-select>
							</b-form-group>
							<b-form-group class="wants-help-question">
								<label>Would you like help addressing this need?</label>
								<b-form-select v-model="newResponse.wantsHelp" :options="yesNoLongOptions"></b-form-select>
							</b-form-group>
							<b-form-group>
								<label>Comments</label>
								<!-- Limitations with b-form-text-area require the model to be data and not a computed property. -->
								<b-form-textarea v-model="comments" rows="3"></b-form-textarea>
							</b-form-group>
							<b-button type="submit" variant="primary">Submit</b-button>
						</b-form>
    				</b-tab>
  				</b-tabs>
              </div>
              </b-card>
      	   </template>
		</b-table>
		<!-- Modal for Creating/Editing a Clinician Review -->
    	<b-modal id="modalEditRow" ref="modal" @hide="resetModal" :title="modalEditRow.title" @ok.prevent="submitClinicianReview">
      		<form @submit.stop.prevent="submitClinicianReview">
      			<b-form-group
      				id="clinicianPriorityLabel"
       				label="Clinician Priority"
      				label-for="clinicianPriority">
      			<b-form-select id="clinicianPriority" v-model="modalEditRow.prioritySelected" :options="priorityOptions" />
      			</b-form-group>
      			<b-form-group
      				id="patientReadinessLabel"
       				label="Patient Readiness"
      				label-for="patientReadiness">
      			<b-form-select id="patientReadiness" v-model="modalEditRow.readinessSelected" :options="readinessOptions" />
      			</b-form-group>
      			<b-form-group
      				id="referredLabel"
       				label="Referred"
      				label-for="referred">
      			<b-form-select id="referred" v-model="modalEditRow.referred" :options="yesNoOptions" />
      			</b-form-group>
      			<b-form-group
      				id="referralCompleteLabel"
       				label="Referral Complete"
      				label-for="referralComplete">
      			<b-form-select id="referralComplete" v-model="modalEditRow.referralComplete" :options="yesNoOptions" />
      			</b-form-group>
      		</form>
    	</b-modal>
		</div>
		`,
		data: {
			patient,
			contextPath,
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
	        emptyOption: "---",
	        readinessOptions: [],
	        yesNoOptions: [{value: true, text:'Y'}, {value: false, text:'N'}],
	        yesNoLongOptions: [{value: true, text:'Yes'}, {value: false, text:'No'}],
	        reviews: [],
	        questionnaires: [],
	        responses: [],
	        expandedRow: {},
	        tabIndex: 0,
	        selectedResponseDate: null,
	        comments: null,
	        currentComments: null,
	        dismissCountDown: 0,
	        modalEditRow: new ModalEditRow()
		},
		mounted() {
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
					this.priorityOptions.unshift({value:null, text:this.emptyOption});
				}
			});
			$.ajax({
				url: contextPath + '/api/patient_readiness/',
				contentType: 'application/json',
				success: data => {
					this.readinessOptions = data._embedded.patientReadinesses.map(it => {
						return {value: it._links.self.href, text: it.description}
					});
					this.readinessOptions.unshift({value:null, text:this.emptyOption});
				}
			});
			$.ajax({
				url: `${this.contextPath}/api/clinician_review/search/findByPatientId?id=${this.patient}`,
				contentType: 'application/json',
				success: data => {
					this.reviews = data._embedded.clinicianReviews;
				}
			});
			$.ajax({
				url: `${this.contextPath}/api/sdh_domain_questionnaire/`,
				contentType: 'application/json',
				success: data => {
					this.questionnaires = data._embedded.sdhDomainQuestionnaires;
				}
			});
			$.ajax({
				url: `${this.contextPath}/api/questionnaire_response/search/findByPatientId?id=${this.patient}`,
				contentType: 'application/json',
				success: data => {
					this.responses = data._embedded.questionnaireResponses;
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
		    rowDetails(row, target) {
		    	if (row.detailsShowing === false) {
		    		// Close all other rows
		    		this.emphasized.forEach(item => {
		    			item._showDetails = (item === row.item);
		    		});		
		    		this.expandedRow = row;
	    			this.tabIndex = 0;
		    		// Set the selected response date to current when first expanded
		    		if (this.hasResponseForDomain) {
		    			this.selectedResponseDate = this.currentResponse.createdAt;
		    		}
		    	} else {
		    		row.toggleDetails();
		    	}
		    },
		    resetModal() {
		    	return new ModalEditRow();
		    },
		    submitClinicianReview() {
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
					url = `${this.contextPath}/api/clinician_review/${clinicianReviewId}`;
					method = 'PATCH';
				} else {
					url = `${this.contextPath}/api/clinician_review/`;
					method = 'POST';
					obj.patient = `${this.contextPath}/api/patient/${this.patient}`;
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
							url: `${this.contextPath}/api/clinician_review/search/findByPatientId?id=${this.patient}`,
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
		    },
		    validateResponse() {
		    	const blankAnswer = this.newResponse.questions.some(q => q.answer === "");
		    	if (blankAnswer) {
		    		alert("Please answer all questions");
		    	} else {
		    		this.submitResponse();
		    	}
		    },
		    submitResponse() {
		    	let obj = {
		    		answerJson: JSON.stringify(this.newResponse.questions),
		    		wantsHelp: this.newResponse.wantsHelp,
		    		comments: this.comments,
		    		patient: `${this.contextPath}/api/patient/${this.patient}`,
		    		sdhDomainQuestionnaire: this.newResponse.href
		    	}
				$.ajax({
					url: `${this.contextPath}/api/questionnaire_response/`,
					method: 'POST',
					data: JSON.stringify(obj),
					contentType: 'application/json',
					success: data => {
						$.ajax({
							url: `${this.contextPath}/api/questionnaire_response/search/findByPatientId?id=${this.patient}`,
							contentType: 'application/json',
							success: data => {
								this.responses = data._embedded.questionnaireResponses;
								this.selectedResponseDate = this.currentResponse.createdAt;
								this.tabIndex = 0;
								this.comments = null;
							}
						});
					}
				});
		    	
		    },
		    editComments() {
		    	let obj = {
			    	comments: this.currentComments,
			    }
				$.ajax({
					url: `${this.contextPath}/api/questionnaire_response/${this.currentResponse.id}`,
					method: 'PATCH',
					data: JSON.stringify(obj),
					contentType: 'application/json',
					success: data => {
						$.ajax({
							url: `${this.contextPath}/api/questionnaire_response/search/findByPatientId?id=${this.patient}`,
							contentType: 'application/json',
							success: data => {
								this.responses = data._embedded.questionnaireResponses;
								// Trigger the dismissable element indicating a successful save
								this.dismissCountDown = 3;
							}
						});
					}
				});
		    }
		},
		watch: {
			selectedResponseDate: function(newDate, oldDate) {
				// This triggers the UI to update the selectedResponse
			}
		},
		computed: {
			clinicianReviewSummary () {				
				return this.domainOptions.map( domain => {
					let reviewForDomain = this.reviews.filter( review => {
						return review.domain === domain.text;
					});
					if (reviewForDomain.length === 0) {
						return {id: '', domain: domain.text, last_reviewed: 'NA', clinician_priority:this.emptyOption, patient_readiness: this.emptyOption, referred: this._selectText(this.yesNoOptions, false), referral_complete: this._selectText(this.yesNoOptions, false)};
					} else {
						const review = reviewForDomain[0]; // Unique constraint prevents more than one
						const priority = review.clinicianPriority ? review.clinicianPriority.description : this.emptyOption;
						const readiness = review.patientReadiness ? review.patientReadiness.description : this.emptyOption;
						return {id: review.id, domain: domain.text, last_reviewed: this._formatDate(review.updatedAt), clinician_priority: priority, patient_readiness: readiness, referred: this._selectText(this.yesNoOptions, review.referred), referral_complete: this._selectText(this.yesNoOptions, review.referralComplete)};						
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
		    },
		    expandedDomain() {
		    	return this.expandedRow.item.domain;
		    },
		    responsesForDomain() {
		    	const responses = this.responses.filter(r => r.domain === this.expandedDomain);
		    	const mapped = responses.map(r => {
		    		return {id: r.id, createdAt: r.createdAt, questions: JSON.parse(r.answerJson), wantsHelp:r.wantsHelp, comments:r.comments}
		    	});
		    	return mapped;
		    },
		    responseDates() {
		    	// Select options for response date are in reverse creation order
		    	return this.responsesForDomain.map(r => r.createdAt).sort().reverse().map(date => { 
		    		return {value: date, text: this._formatDate(date)};
		    	});
		    },
		    hasResponseForDomain() {
		    	return this.responsesForDomain.length > 0;
		    },
		    currentResponse() {
		    	if (this.hasResponseForDomain === true) {
			    	const currentResponse = this.responsesForDomain.reduce(function(r1, r2) {
	    				return r1.createdAt > r2.createdAt ? r1 : r2;
	    			});
			    	this.currentComments = currentResponse.comments;
			    	return currentResponse;
		    	}
		    	return null;
		    },
		    selectedResponse() {
		    	if (this.hasResponseForDomain === true) {
			    	const selectedResponse = this.responsesForDomain.find(r => r.createdAt === this.selectedResponseDate)
			    	return selectedResponse;
		    	}
		    	return null;
		    },
		    newResponse() {
		    	// Get the questionnaire for the expanded domain
		    	const domainQuestionnaire = this.questionnaires.filter(q => q.sdhDomain === this.expandedDomain);
		    	const questions = JSON.parse(domainQuestionnaire[0].questionJson).questions;
		    	// Start with blank answers for each question
		    	questions.forEach(q => q.answer = "");
		    	return {domain: this.expandedDomain, questions: questions, wantsHelp: false, href:domainQuestionnaire[0]._links.self.href};
		    }
		}
	});
})();
