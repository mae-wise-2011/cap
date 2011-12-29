dataSource {
    //pooled = true
    //driverClassName = "org.hsqldb.jdbcDriver"
    //username = "sa"
    //password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
    development {
        dataSource {
            //dbCreate = "create-drop" // one of 'create', 'create-drop','update'
            url = "jdbc:hsqldb:mem:devDB"
		driverClassName = "org.hsqldb.jdbcDriver"	
		pooled = true
		dbCreate = "create-drop"
		//url = "jdbc:mysql://se-labor-7.f4.htw-berlin.de:3306/cap_dev"
		//driverClassName = "com.mysql.jdbc.Driver"
		//username = "root"
		//password = "MAE_2011"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:hsqldb:mem:testDb"
        }
    }
    production {
        dataSource {
            //dbCreate = "update"
            //url = "jdbc:hsqldb:file:prodDb;shutdown=true"
		pooled = true
		dbCreate = "create-drop"
		url = "jdbc:mysql://se-labor-7.f4.htw-berlin.de:3306/cap_dev"
		driverClassName = "com.mysql.jdbc.Driver"
		username = "root"
		password = "MAE_2011"
        }
    }
}
