--用户权限视图，赋予的为T,未赋予的为F
create or replace view userrights_view
as
select a.category_id,a.function_id, b.category_name,c.function_name ,'T' as isset , d.user_id
from rights_category_function a, rights_category b, rights_function c, user_admin d
where a.category_id = b.category_id and a.function_id = c.function_id
and (a.category_id,a.function_id) not in (select rights_category_id,rights_function_id from rights_override where override=false and user_id = d.user_id)
and 
a.category_id in
(select category_id from rights_default where user_type_id in (select user_type_id from user_admin where user_id=d.user_id))
union
select a.category_id,a.function_id, b.category_name,c.function_name ,'F' as isset , d.user_id
from rights_category_function a, rights_category b, rights_function c, user_admin d
where a.category_id = b.category_id and a.function_id = c.function_id
and (a.category_id,a.function_id) not in (select rights_category_id,rights_function_id from rights_override where override=true and user_id = d.user_id)
and 
a.category_id not in
(select category_id from rights_default where user_type_id in (select user_type_id from user_admin where user_id=d.user_id))
union
select a.rights_category_id, a.rights_function_id, b.category_name,c.function_name , 'T' as isset , a.user_id
from rights_override a, rights_category b, rights_function c
where a.rights_category_id = b.category_id and a.rights_function_id = c.function_id
and a.override = true
union
select a.rights_category_id, a.rights_function_id, b.category_name,c.function_name , 'F' as isset , a.user_id
from rights_override a, rights_category b, rights_function c
where a.rights_category_id = b.category_id and a.rights_function_id = c.function_id
and a.override = false

--获取当前赋予权限的视图
create or replace view userrights_effective
as
select a.category_id,a.function_id, b.category_name,c.function_name , d.user_id
from rights_category_function a, rights_category b, rights_function c, user_admin d
where a.category_id = b.category_id and a.function_id = c.function_id
and (a.category_id,a.function_id) not in (select rights_category_id,rights_function_id from rights_override where override=false and user_id = d.user_id)
and 
a.category_id in
(select category_id from rights_default where user_type_id in (select user_type_id from user_admin where user_id=d.user_id))
union
select a.rights_category_id, a.rights_function_id, b.category_name,c.function_name , a.user_id
from rights_override a, rights_category b, rights_function c
where a.rights_category_id = b.category_id and a.rights_function_id = c.function_id
and a.override = true

select * from userrights_view where user_id=2
