package org.imogene.epicam.domain.entity.backup;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.imogene.lib.common.binary.file.BinaryFile;
import org.imogene.lib.common.entity.GeoField;
import org.imogene.lib.common.entity.ImogActorImpl;
import org.imogene.lib.common.entity.ImogBeanImpl;
import org.imogene.lib.common.entity.IsGeoreferenced;

import org.imogene.epicam.domain.entity.CasTuberculose;
import org.imogene.epicam.domain.entity.backup.CasTuberculoseBck;
import org.imogene.epicam.domain.entity.ExamenMicroscopie;
import org.imogene.epicam.domain.entity.ExamenATB;
import org.imogene.epicam.domain.entity.PriseMedicamenteuse;
import org.imogene.epicam.domain.entity.PriseMedicamenteuse;
import org.imogene.epicam.domain.entity.RendezVous;

/**
 * ImogBean implementation for the entity CasTuberculose
 * @author MEDES-IMPS
 */
public class CasTuberculoseCloner {

	public static CasTuberculoseBck cloneEntity(CasTuberculose entity) {
		CasTuberculoseBck bck = new CasTuberculoseBck();
		bck.setTraceId(UUID.randomUUID().toString());
		bck.setId(entity.getId());
		bck.setCreated(entity.getCreated());
		bck.setCreatedBy(entity.getCreatedBy());
		bck.setModified(entity.getModified());
		bck.setModifiedBy(entity.getModifiedBy());
		bck.setModifiedFrom(entity.getModifiedFrom());
		bck.setUploadDate(entity.getUploadDate());
		bck.setDeleted(entity.getDeleted());
		bck.setVersion(entity.getVersion());

		bck.setIdentifiant(entity.getIdentifiant());

		bck.setNumRegTB(entity.getNumRegTB());

		if (entity.getPatient() != null) {
			bck.setPatient(entity.getPatient().getId());
		}

		bck.setDateDebutTraitement(entity.getDateDebutTraitement());

		bck.setTypePatient(entity.getTypePatient());

		bck.setTypePatientPrecisions(entity.getTypePatientPrecisions());

		bck.setFormeMaladie(entity.getFormeMaladie());

		bck.setExtraPulmonairePrecisions(entity.getExtraPulmonairePrecisions());

		bck.setCotrimoxazole(entity.getCotrimoxazole());

		bck.setAntiRetroViraux(entity.getAntiRetroViraux());

		bck.setFumeur(entity.getFumeur());

		bck.setFumeurArreter(entity.getFumeurArreter());

		if (entity.getExamensMiscrocopies() != null) {
			StringBuilder builder = new StringBuilder();
			for (ExamenMicroscopie i : entity.getExamensMiscrocopies()) {
				builder.append(i.getId()).append(";");
			}
			bck.setExamensMiscrocopies(builder.toString());
		}

		if (entity.getExamensATB() != null) {
			StringBuilder builder = new StringBuilder();
			for (ExamenATB i : entity.getExamensATB()) {
				builder.append(i.getId()).append(";");
			}
			bck.setExamensATB(builder.toString());
		}

		if (entity.getRegimePhaseInitiale() != null) {
			bck.setRegimePhaseInitiale(entity.getRegimePhaseInitiale().getId());
		}

		if (entity.getRegimePhaseContinuation() != null) {
			bck.setRegimePhaseContinuation(entity.getRegimePhaseContinuation()
					.getId());
		}

		if (entity.getPriseMedicamenteusePhaseInitiale() != null) {
			StringBuilder builder = new StringBuilder();
			for (PriseMedicamenteuse i : entity
					.getPriseMedicamenteusePhaseInitiale()) {
				builder.append(i.getId()).append(";");
			}
			bck.setPriseMedicamenteusePhaseInitiale(builder.toString());
		}

		if (entity.getPriseMedicamenteusePhaseContinuation() != null) {
			StringBuilder builder = new StringBuilder();
			for (PriseMedicamenteuse i : entity
					.getPriseMedicamenteusePhaseContinuation()) {
				builder.append(i.getId()).append(";");
			}
			bck.setPriseMedicamenteusePhaseContinuation(builder.toString());
		}

		if (entity.getRendezVous() != null) {
			StringBuilder builder = new StringBuilder();
			for (RendezVous i : entity.getRendezVous()) {
				builder.append(i.getId()).append(";");
			}
			bck.setRendezVous(builder.toString());
		}

		bck.setDateFinTraitement(entity.getDateFinTraitement());

		bck.setDevenirMalade(entity.getDevenirMalade());

		bck.setObservation(entity.getObservation());
		return bck;
	}
}
