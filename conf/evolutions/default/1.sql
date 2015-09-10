# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table bet (
  id                        integer primary key AUTOINCREMENT,
  user                      varchar(255),
  raceid                    integer,
  bettype                   varchar(255),
  runnerid                  integer,
  unit                      varchar(255))
;

create table bettype (
  bettype                   varchar(255) primary key,
  raceid                    integer)
;

create table meeting (
  id                        integer primary key AUTOINCREMENT,
  country                   varchar(255))
;

create table race (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255),
  raceclass                 varchar(255),
  meetingid                 integer)
;

create table runner (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255),
  raceid                    integer)
;

create table unit (
  unit                      varchar(255) primary key)
;

create table user (
  id                        integer primary key AUTOINCREMENT,
  username                  varchar(255),
  password                  varchar(255))
;




# --- !Downs

PRAGMA foreign_keys = OFF;

drop table bet;

drop table bettype;

drop table meeting;

drop table race;

drop table runner;

drop table unit;

drop table user;

PRAGMA foreign_keys = ON;

