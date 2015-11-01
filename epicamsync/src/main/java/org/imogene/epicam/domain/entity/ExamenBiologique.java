package org.imogene.epicam.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

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

import org.hibernate.annotations.Formula;
import org.imogene.lib.common.binary.file.BinaryFile;
import org.imogene.lib.common.constants.NotificationConstants;
import org.imogene.lib.common.entity.GeoField;
import org.imogene.lib.common.entity.ImogActorImpl;
import org.imogene.lib.common.entity.ImogEntityImpl;
import org.imogene.lib.common.entity.IsGeoreferenced;

/**
 * ImogBean implementation for the entity ExamenBiologique
 * @author MEDES-IMPS
 */
@Entity
public class ExamenBiologique extends ImogEntityImpl {

	public static interface Columns {
		public static final String PATIENT = "patient";

		public static final String DATE = "date";

		public static final String POIDS = "poids";

		public static final String OBSERVATIONS = "observations";

	}

	private static final long serialVersionUID = -2465873529027994251L;

	/* Description group fields */

	@ManyToOne
	@JoinColumn(name = "examensBiologiquesPatient_id")
	private Patient patient;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private Double poids;

	@Column(columnDefinition = "TEXT")
	private String observations;

	/**
	 * Constructor
	 */
	public ExamenBiologique() {
	}

	/* Getters and Setters for Description group fields */

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient value) {
		patient = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date value) {
		date = value;
	}

	public Double getPoids() {
		return poids;
	}

	public void setPoids(Double value) {
		poids = value;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String value) {
		observations = value;
	}

}
