package com.project.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRINCIPAL")
@EntityListeners (AuditingEntityListener.class)
public class Principal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "principal_id")
    private long principalId;

    @Column(name = "principal")
    private String sum;

    @Column(name = "rate")
    private String rate;

    @Column(name = "time")
    private String time;

    @Column(name = "total_amount")
    private String totalAmount;

    @Column(name = "paid_amount")
    private String amountPaid;

    @Column(name = "created_date")
    private String issueDate;

    @Column(name = "due_date")
    private String dueDate;
}