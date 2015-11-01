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
 * ImogBean implementation for the entity ExamenSerologie
 * @author MEDES-IMPS
 */
@Entity
public class ExamenSerologie extends ImogEntityImpl {

	public static interface Columns {
		public static final String PATIENT = "patient";

		public static final String DATETEST = "datetest";

		public static final String NATURE = "nature";

		public static final int NATURE_VIH = 0;

		public static final int NATURE_CD4 = 1;

		public static final String RESULTATVIH = "resultatvih";

		public static final int RESULTATVIH_POSITIF = 0;

		public static final int RESULTATVIH_NEGATIF = 1;

		public static final String RESULTATCD4 = "resultatcd4";

	}

	private static final long serialVersionUID = -1257449257282930864L;

	/* Description group fields */

	@ManyToOne
	@JoinColumn(name = "serologiesPatient_id")
	private Patient patient;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTest;

	private String nature;

	private String resultatVIH;

	private Integer resultatCD4;

	/**
	 * Constructor
	 */
	public ExamenSerologie() {
	}

	/* Getters and Setters for Description group fields */

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient value) {
		patient = value;
	}

	public Date getDateTest() {
		return dateTest;
	}

	public void setDateTest(Date value) {
		dateTest = value;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String value) {
		nature = value;
	}

	public String getResultatVIH() {
		return resultatVIH;
	}

	public void setResultatVIH(String value) {
		resultatVIH = value;
	}

	public Integer getResultatCD4() {
		return resultatCD4;
	}

	public void setResultatCD4(Integer value) {
		resultatCD4 = value;
	}

}
