package br.com.consultemed.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedQueries({ @NamedQuery(name = "Agendamento.findAll", query = "SELECT a FROM Agendamento a")})
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "TB_AGENDAMENTOS")
public class Agendamento implements Serializable {
	private static final long serialVersionUID = 1L;
		
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Getter
	@Setter
	@Column(name = "DATA")
	@Temporal(TemporalType.DATE)
	private Date agData;
	
	@Getter
	@Setter
	@Column(name = "HORA")
	private String agHora;
			
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "PACIENTE", nullable=false)
	private Paciente agPaciente = new Paciente();
		
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "MEDICO", nullable=false)
	private Medico agMedico = new Medico();

}