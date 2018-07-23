package cn.eddy.utils.stringUtils;

public class StringUtils {

	public static void main(String[] args) {
		StringBuffer sql = new StringBuffer();
		sql// ----- begin -- 卖进信息 ---
		.append("select psi.ID,"  // 0
				+ "psi.PROJECT_ID,"  // 1
				+ "psi.CHANNEL_SYNC_ID,"  // 2
				+ "psi.PLAN_START_TIME,"  // 3
				+ "psi.PLAN_FINISHED_TIME,"  // 4
				+ "psi.EMP_CODE,"  // 5
				+ "psi.CITY_EMP_CODE,"  // 6
				+ "psi.STATUS,"  // 7
				+ "psi.EXEC_STATUS,"  // 8
				+ "psi.AUDIT_STATUS,"  // 9
				+ "psi.SCHEDULE_NUM,"  // 10
				+ "psi.SCHEDULE_TYPE,"  // 11
				+ "psi.AUDI_USER_ID,"  // 12
				+ "psi.AUDI_DATE,"  // 13
				+ "psi.CREATE_TIME,"  // 14
				+ "psi.CREATE_USER,"  // 15
				+ "psi.UPDATE_TIME,"  // 16
				
				+ "c.CHANNEL_ADMIN_DIV_ID,"  // 17
				+ "c.CHANNEL_CODE,"  // 18
				+ "c.CHANNEL_NAME,"  // 19
				+ "c.CHANNEL_ADDRESS,"  // 20
				+ "c.CHANNEL_AD_CODE,"  // 21
				+ "c.CHANNEL_AD_NAME,"  // 22
				+ "c.CHANNEL_PROVINCE_AD_CODE,"  // 23
				+ "c.CHANNEL_PROVINCE_AD_NAME,"  // 24
				+ "c.CHANNEL_CITY_AD_CODE,"  // 25
				+ "c.CHANNEL_CITY_AD_NAME,"  // 26
				
				+ "p.code projCode, "  // 27
				+ "p.name projName, "  // 28
				+ "p.org_code, "  // 29
				+ "p.customer projCustomer, "  // 30
				+ "p.production_brand, "  // 31
				+ "p.customer_project_name, "  // 32
				+ "p.customer_project_type");  // 33
		// For sales and execution date.
		sql.append(" ,ed.execDateCount ");  // 34
		sql.append(" ,soc.planStoreOpenCount ");  // 35
		sql.append(" ,sod.planStoreOpenDays ");  // 36
		sql.append(" ,sed.planExecDays ");  // 37
		sql.append(" ,psi.OLD_PLAN_START_TIME "  // 38
		+ " ,psi.OLD_PLAN_FINISHED_TIME ");  // 39
		sql.append(" ,psi.PLAN_SALES_COUNT"); // 40
		
		sql.append(" ,psi.CUST_DEPUTY_NAME"); // 41
		sql.append(" ,psi.CUST_DEPUTY_PHONE"); // 42
		sql.append(" ,psi.CUST_DIRECTOR_NAME"); // 43
		sql.append(" ,psi.CUST_DIRECTOR_PHONE"); // 44
		sql.append(" ,psi.CUST_MANAGER_NAME"); // 45
		sql.append(" ,psi.CUST_MANAGER_PHONE"); // 46
		sql.append(" ,psi.SELLIN_CHANNEL ");  // 47
		
		sql.append(" ,psi.CUST_CHANNEL ");  // 48
		sql.append(" ,psi.CUST_SYS ");  // 49
		sql.append(" ,psi.CUST_CHANNEL_CODE ");  // 50
		sql.append(" ,psi.CUST_CHANNEL_NAME ");  // 51
		sql.append(" ,psi.PLAN_EXEC_DATE_TIME ");  // 52
		sql.append(" ,psi.PLAN_COM_EXEC_DATE_TIME ");  // 53
		sql.append(" ,psi.PLAN_THREE_PAY_EXEC_DATE_TIME ");  // 54
		sql.append(" ,psi.PLAN_EXEC_STORE_TIME ");  // 55
		sql.append(" ,psi.PLAN_COM_EXEC_STORE_TIME ");  // 56
		sql.append(" ,psi.PLAN_THREE_PAY_EXEC_STORE_TIME ");  // 57
		sql.append(" ,psi.PLAN_EXHIBIT_TYPE ");  // 58
		sql.append(" ,psi.PLAN_EXHIBIT_START_TIME ");  // 59
		sql.append(" ,psi.PLAN_EXHIBIT_END_TIME ");  // 60
		sql.append(" ,psi.PLAN_STORE_PATROL_COUNT ");  // 61
		sql.append(" ,psi.PLAN_WEEK_STORE_PATROL_COUNT ");  // 62
		sql.append(" ,psi.PLAN_MONTH_STORE_PATROL_COUNT ");  // 63
		sql.append(" ,psi.TOTAL_SALES_TARGET ");  // 64
		sql.append(" ,psi.DAILY_SOTRE_SALES_TARGET ");  // 65
		sql.append(" ,psi.AREA_MANAGER_CODE ");  // 66
		sql.append(" ,psi.PLAN_EXHIBIT_NUM ");  // 67
		
		for (int i = 1; i < 101; i++) {
			sql.append(", psi.C" + i);
		}
		
		sql.append(" from DM_PROJECT_SELLIN_INFO psi ");
		sql.append(" inner join DM_PROJECT p on psi.PROJECT_ID=p.ID ");
		sql.append(" inner join DM_CHANNEL_SYNC c  on psi.CHANNEL_SYNC_ID=c.ID");
		sql.append(" left join (select t.project_sellin_info_id, count(*) execDateCount from DM_PROJECT_SELLIN_EXECDATE t "
				+ " where 1=1 ");
		
		// 执行日开始时间
//		if (search.getExecBeginDate() != null) {
//			sqlFrom.append(" and to_char(t.exec_date, 'yyyy-mm-dd') >= '" + ConstantsMecool.SIMPLE_DATE_FORMAT.format(search.getExecBeginDate()) + "'");
//		}
//		// 执行日结束时间
//		if (search.getExecEndDate() != null) {
//			sqlFrom.append(" and to_char(t.exec_date, 'yyyy-mm-dd') <= '" + ConstantsMecool.SIMPLE_DATE_FORMAT.format(search.getExecEndDate()) + "'");
//		}
		sql.append(" group by t.project_sellin_info_id) ed on ed.project_sellin_info_id=psi.id ");
		sql.append(" left join (select t.PROJECT_SELLIN_INFO_ID, count(*) planStoreOpenCount from DM_PROJECT_SELLIN_STORE_OPEN t group by t.PROJECT_SELLIN_INFO_ID) soc on soc.project_sellin_info_id=psi.id ");
		sql.append(" left join (select t.PROJECT_SELLIN_INFO_ID, concat_array(CAST(collect(to_char(t.so_plan_open_time, 'yyyy/mm/dd')) as tab_varchar2)) planStoreOpenDays from DM_PROJECT_SELLIN_STORE_OPEN t group by t.PROJECT_SELLIN_INFO_ID) sod on sod.project_sellin_info_id=psi.id ");
		sql.append(" left join (select t.PROJECT_SELLIN_INFO_ID, concat_array(CAST(collect(to_char(t.exec_date, 'yyyy/mm/dd')) as tab_varchar2)) planExecDays from DM_PROJECT_SELLIN_EXECDATE t group by t.PROJECT_SELLIN_INFO_ID) sed on sed.project_sellin_info_id=psi.id ");

		//
//		if (search.getExecBeginDate() != null || search.getExecEndDate() != null) {
//			sqlWhere.append(" and ed.execDateCount > 0 ");
//		}
//		if (search.getProject() != null) {
//			sqlWhere.append(" and psi.PROJECT_ID=" + search.getProject().getId());
//		}
		// 终端类别
//		if (StringUtils.isNotEmpty(search.getCategory())) {
//			if (StringUtils.isNotEmpty(search.getSysCategory())) {
//				sqlWhere.append(" and c.channel_sys_cat='" + search.getSysCategory() + "'");
//			} else {
//				List<Object> vs = mdPosSysTypeRepo.findChannelSysCatByKindCode(search.getCategory());
//				if (vs != null && vs.size() > 0) {
//					sqlWhere.append(" and ").append(SQLUtil.getInSubSql("c.channel_sys_cat", vs, SQLUtil.IN_SQL_OBJECT_TYPE_STRING));
//				}
//			}
//		}
		//部门
//		if (StringUtils.isNotEmpty(search.getDept())) {
//			sqlFrom.append(", md_org_emp oe, MD_EMP e");
//			sqlWhere.append(" and oe.emp_pk=e.emp_pk and oe.org_code='" + search.getDept() + "' ");
//			sqlWhere.append(" and (psi.emp_code=e.emp_code or psi.city_emp_code=e.emp_code) ");
//		}
		
		// 统计周期开始时间
//		if (search.getBeginDate() != null) {
//			sqlWhere.append(" and (to_char(psi.OLD_PLAN_START_TIME, 'yyyy-mm-dd') >= '" + ConstantsMecool.SIMPLE_DATE_FORMAT.format(search.getBeginDate()) + "'");
//			sqlWhere.append(" or to_char(psi.OLD_PLAN_FINISHED_TIME, 'yyyy-mm-dd') >= '" + ConstantsMecool.SIMPLE_DATE_FORMAT.format(search.getBeginDate()) + "')");
//		}
		// 统计周期结束时间
//		if (search.getEndDate() != null) {
//			sqlWhere.append(" and (to_char(psi.OLD_PLAN_START_TIME, 'yyyy-mm-dd') <= '" + ConstantsMecool.SIMPLE_DATE_FORMAT.format(search.getEndDate()) + "'");
//			sqlWhere.append(" or to_char(psi.OLD_PLAN_FINISHED_TIME, 'yyyy-mm-dd') <= '" + ConstantsMecool.SIMPLE_DATE_FORMAT.format(search.getEndDate()) + "')");
//		}
		
		//卖进状态
//		if(search.getSellinStatus() != null && !"-1".equals(search.getSellinStatus().toString())){
//			sqlWhere.append(" and psi.STATUS =" + search.getSellinStatus());
//		}

		//卖进执行状态
//		if(search.getSellinExecStatus() != null && !"-1".equals(search.getSellinExecStatus().toString())){
//			sqlWhere.append(" and psi.EXEC_STATUS =" + search.getSellinExecStatus());
//		}

		//门店编号
//		if(search.getStoreCode() != null && search.getStoreCode().trim().length() > 1){
//			sqlWhere.append(" and c.channel_code like '%" + search.getStoreCode() + "%'");
//		}

		//门店名称
//		if(search.getStoreName() != null && search.getStoreName().trim().length() > 1){
//			sqlWhere.append(" and c.channel_name like '%" + search.getStoreName() + "%'");
//		}
		
		// 更新日期 开始时间
//		if (search.getUpdateBeginDate() != null) {
//			sqlWhere.append(" and to_char(psi.UPDATE_TIME, 'yyyy-mm-dd') >= '" + ConstantsMecool.SIMPLE_DATE_FORMAT.format(search.getUpdateBeginDate()) + "'");
//		}
		// 更新日期 结束时间
//		if (search.getUpdateEndDate() != null) {
//			sqlWhere.append(" and to_char(psi.UPDATE_TIME, 'yyyy-mm-dd') <= '" + ConstantsMecool.SIMPLE_DATE_FORMAT.format(search.getUpdateEndDate()) + "'");
//		}

		// 卖进序号
//		if(search.getScheduleNumSearch() != null && !"-1".equals(search.getScheduleNumSearch().toString())){
//			sqlWhere.append(" and psi.SCHEDULE_NUM =" + search.getScheduleNumSearch());
//		}
		sql.append(", (select pada.MANAGED_ADMINDIV_ADCODE from DM_USER dmuser, DM_PERMROLE_ADMIN_DIV_ADCODE pada "
				+ " where dmuser.PERMISSION_ROLE=pada.PERMISSION_ROLE and dmuser.id='1147636942') adtmp ");
		sql.append(" where 1=1 ");
		sql.append(" and c.channel_ad_code=adtmp.MANAGED_ADMINDIV_ADCODE ");
//		if (ConstantsMecool.OrgType.CUSTOMER.getType().equals(user.getOrgType())) {
//			sqlFrom.append(", (select pada.MANAGED_ADMINDIV_ADCODE from DM_USER dmuser, DM_PERMROLE_ADMIN_DIV_ADCODE pada "
//					+ " where dmuser.PERMISSION_ROLE=pada.PERMISSION_ROLE and dmuser.id=" + user.getId() + ") adtmp ");
//			sqlWhere.append(" and c.channel_ad_code=adtmp.MANAGED_ADMINDIV_ADCODE ");
//		} else {
//			if (user.getDmPermissionRole().getIsAdvanced()) {
//				boolean checkDibAd = permroleAdminDivAdcodeRepo.checkDivAd(user);
//				boolean checkCustomerProject = permissionroleCustCodeRepo.checkCustomerProject();
//				// 高级管理者：			  查看范围: 分配的部门 (项目)& 分配的城市 & 分配的客户   
//				//  分配的部门
//				sqlFrom.append(" , dm_permissionrole_org_code poc ");
//				sqlWhere.append(" and p.org_code=poc.managed_org_code and poc.permission_role = " + user.getDmPermissionRole().getId());
//				//  分配的城市
//				if (checkDibAd) {
//					sqlFrom.append(" , dm_permrole_admin_div_adcode da ");
//					sqlWhere.append(" and c.channel_ad_code=da.managed_admindiv_adcode  and da.permission_role = " + user.getDmPermissionRole().getId());
//				}
//				//  分配的客户
//				if (checkCustomerProject) {
//					sqlFrom.append(" , DM_PERMISSIONROLE_CUST_CODE pcc ");
//					sqlWhere.append(" and  p.customer = pcc.MANAGED_CUST_CODE and pcc.PERMISSION_ROLE = " + user.getDmPermissionRole().getId());
//				}
////						sql.append(sqlFrom).append(sqlWhere);
//			} else {
//
//				// 非高级管理者
//				//  查看范围：自己所在项目项目架构下项目下属的数据
//				
//				if(search.getProject().getId()!=null){
//					
//					
//					String permission = " select distinct ep.emp_code "
//								+" from DM_PROJECT_EMPLOYEE t "
//								+" inner join md_emp ep "
//								+" on t.emp_id = ep.emp_pk "
//								+" where 1 = 1 "
//								+" and t.project="+search.getProject().getId()
//								+" start with t.emp_id ="+user.getEmpPk()
//								+" connect by prior t.id = t.parent";
//					StringBuilder sb = new StringBuilder();
//					List<Object> obj = getEntityManager().createNativeQuery(permission.toString()).getResultList();
//					if (obj != null && obj.size() > 0) {
//						for (int i = 0; i < obj.size(); i++) {
//							Object objs = (Object) obj.get(i);
//							sb.append("'"+objs.toString()+"',");
//						}
//						sb= new StringBuilder(sb.substring(0, sb.length()-1));
//						sqlWhere.append(" and ( ");
//						sqlWhere.append(" psi.emp_code in ("+sb.toString()+") ");
//						sqlWhere.append("or psi.city_emp_code in ("+sb.toString()+") ");
//						sqlWhere.append(" ) ");
//					}
//					
//				}
//		// 城市
//		// Get all of ad.
//		if (StringUtils.isNotEmpty(search.getProvince())) {
//			if (StringUtils.isNotEmpty(search.getCity())) {
//				if (StringUtils.isNotEmpty(search.getAd())) {
//					String searchCode=search.getAd().substring(0, 6);
//					sqlWhere.append(" and c.channel_ad_code like '" + searchCode + "%'");
//				} else {
//					String searchCode=search.getCity().substring(0, 4);
//					sqlWhere.append(" and c.channel_ad_code like '" + searchCode + "%'");
//				}
//			} else {
//				sqlWhere.append(" and c.channel_province_ad_code ='" + search.getProvince() + "'");
//			}
//		}
//	}
//	}
		
		sql.append(" order by psi.PLAN_START_TIME ");
		System.err.println(sql);
}
}
