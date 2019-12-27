-- run on command line like this:
-- sqlite3 Rental.db < create_rental_sqlite_database.sql

drop table if exists Complexes;
create table Complexes (
    ComplexID integer primary key,
    ComplexName text
);

drop table if exists Buildings;
create table Buildings (
    BuildingID integer primary key,
    ComplexID integer references Complexes (ComplexID) on delete restrict on update cascade,
    BuildingName text,
    Address text
);

drop table if exists Apartments;
create table Apartments (
    AptID integer primary key,
    UnitNumber text,
    BuildingID integer references Buildings (BuildingID) on delete restrict on update cascade
);

drop table if exists Tenants;
create table Tenants (
    TenantID integer primary key,
    TenantName text
);

drop table if exists AptTenants;
create table AptTenants (
    TenantID references Tenants (TenantID) on delete restrict on update cascade,
    AptID references Apartments (AptID) on delete restrict on update cascade
);

drop table if exists Requests;
create table Requests (
    RequestID integer primary key,
    Status text,
    AptID integer references Apartments (AptID) on delete cascade,
    Description text
);


.mode csv

.import complexes.csv Complexes
.import buildings.csv Buildings
.import apartments.csv Apartments
.import tenants.csv Tenants
.import tenancies.csv AptTenants
.import requests.csv Requests

