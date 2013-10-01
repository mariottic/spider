 package com.nbb.spider.entity;
 
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
 
 @Entity
 @Table(name="task")
 public class TaskLog extends Task
 {
   private String destination;
 
   @Column(name="destination")
   public String getDestination() {
     return this.destination;
   }
 
   public void setDestination(String destination) {
     this.destination = destination;
   }
 }

