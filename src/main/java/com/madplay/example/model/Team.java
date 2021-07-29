package com.madplay.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name = "team")
public class Team {
	@Id
	private Integer id;
}
