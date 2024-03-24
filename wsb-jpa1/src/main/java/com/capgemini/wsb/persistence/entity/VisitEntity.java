package com.capgemini.wsb.persistence.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String description;

	@Column(nullable = false)
	private LocalDateTime time;

	// One direction
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			orphanRemoval = true
	)
	@JoinColumn(name = "VISIT_ID")
	private Collection<MedicalTreatmentEntity> medicalTreatments;

	// Two direction
	@ManyToOne
	private DoctorEntity doctor;

	// Two direction
	@ManyToOne
	private PatientEntity patient;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}


	public Collection<MedicalTreatmentEntity> getMedicalTreatments() {
		return medicalTreatments;
	}

}
