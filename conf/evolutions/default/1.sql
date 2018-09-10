# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tbprog (
  idpro                     varchar(255) not null,
  name                      varchar(255) not null,
  type                      varchar(255) not null,
  size                      varchar(255) not null,
  color                     varchar(255) not null,
  detail                    varchar(255) not null,
  pattern                   varchar(255) not null,
  price                     double,
  amount                    double,
  sale                      double,
  netprice                  double,
  pic                       varchar(255))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table tbprog;

SET FOREIGN_KEY_CHECKS=1;

