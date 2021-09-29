# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM openjdk

WORKDIR /Noticias

ENTRYPOINT ["java", "-jar", "Noticias-0.0.1-SNAPSHOT.jar"]