# WebScrapingNoticias
WebScraping de noticias do InfoMoney

-Para executar a aplicão é necessario altera os seguintes campos no arquivo PersistemConfig.java:

 Caso o banco utilizado seja o Postgres alterar somente os dados do topido 1;   
  
  1 - No metodo dataSource() alterar:
  
        dataSource.setDriverClassName("org.postgresql.Driver"); //informar o Driver do banco utilizado
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres"); //informar a Url do database;
        dataSource.setUsername("postgres"); //informar o usuario do database;
        dataSource.setPassword(""); //informar a senha do database;
        
   2 - No metodo jpaVendorAdapter() alterar:
   
       jpaVendor.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect"); //informar o dialeto
       
   3 - No metodo  jpaProperties() alterar: 
   
      properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect"); //informar o dialeto
