# WebScrapingNoticias
WebScraping de noticias do InfoMoney

-Para executar a aplicação é necessario alterar os seguintes campos no arquivo PersistemConfig.java:

 Caso o banco utilizado seja o Postgres alterar somente os dados do tópico 1;   
  
  1 - No método dataSource() alterar:
  
        dataSource.setDriverClassName("org.postgresql.Driver"); //informar o Driver do banco utilizado
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres"); //informar a Url do database;
        dataSource.setUsername("postgres"); //informar o usuario do database;
        dataSource.setPassword(""); //informar a senha do database;
        
   2 - No método jpaVendorAdapter() alterar:
   
       jpaVendor.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect"); //informar o dialeto
       
   3 - No método  jpaProperties() alterar: 
   
      properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect"); //informar o dialeto
