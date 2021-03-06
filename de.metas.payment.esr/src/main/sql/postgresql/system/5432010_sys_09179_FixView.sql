create or replace view x_esr_import_in_c_bankstatement_v as
select * from
(
	select bs.AD_CLient_ID, bs.AD_Org_ID, bs.Created, bs.CreatedBy, bs.c_bankstatement_id,  ei.esr_import_id, ei.datedoc
	from esr_importline eil
	join esr_import ei on eil.esr_import_id = ei.esr_import_id
	join c_payment p on eil.c_payment_id = p.c_payment_id
	join c_bankstatementline_ref bslr on p.c_payment_id = bslr.c_payment_id
	join c_bankstatementline bsl on bslr.c_bankstatementline_id = bsl.c_bankstatementline_id
	join c_bankstatement bs on (bsl.c_bankstatement_id = bs.c_bankstatement_id)
	where true
	and eil.esrtrxtype != '999'

	union
	
	select bs.AD_CLient_ID, bs.AD_Org_ID, bs.Created, bs.CreatedBy, bs.c_bankstatement_id,  ei.esr_import_id, ei.datedoc
	from esr_importline eil
	join esr_import ei on eil.esr_import_id = ei.esr_import_id
	join c_payment p on eil.c_payment_id = p.c_payment_id
	join c_bankstatementline bsl on p.c_payment_id = bsl.c_payment_id
	join c_bankstatement bs on (bsl.c_bankstatement_id = bs.c_bankstatement_id)
	where true
	and eil.esrtrxtype != '999'
) as data
	group by c_bankstatement_id, esr_import_id, datedoc,
		AD_CLient_ID, AD_Org_ID, Created, CreatedBy 
	order by esr_import_id
;
