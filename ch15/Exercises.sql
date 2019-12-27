

.mode tab

-- Ex 15.1

select t.TenantID, t.TenantName,
count(*) as NumberApartments
from Tenants t
inner join AptTenants at on t.TenantID = at.TenantID
group by t.TenantID
having NumberApartments > 1;


-- Ex 15.2

select b.BuildingID, b.BuildingName,
count(*) as OpenRequests
from Buildings b
inner join Apartments a on a.BuildingID = b.BuildingID
inner join Requests r on r.AptID = a.AptID
where r.Status = 'Open'
group by b.BuildingID;

-- Ex 15.3

update Requests
set Status = 'Closed'
where AptID in (select AptID from Apartments where BuildingID = 2);


