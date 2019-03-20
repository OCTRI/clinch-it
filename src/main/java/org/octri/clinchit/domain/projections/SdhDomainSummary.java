package org.octri.clinchit.domain.projections;

import org.octri.clinchit.domain.SdhDomain;
import org.springframework.data.rest.core.config.Projection;

/**
 * Summarize the SdhDomain in a more natural way for REST access and the SDH view.
 * 
 * @author yateam
 *
 */
@Projection(name = "summary", types = SdhDomain.class)
public interface SdhDomainSummary extends IdDescriptionSummary {

}

	
