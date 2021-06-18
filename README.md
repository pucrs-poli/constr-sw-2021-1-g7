"# constr-sw-2021-1-g7" 

BRANCH MASTER --> API AUTH KEYCLOAK <br/>
BRANCH ALUNOS --> API STUDENTS/SUBSCRIPTIONS <br/>

AMBIENTE NA AWS: <br/>

KEYCLOAK: http://ec2-54-94-115-41.sa-east-1.compute.amazonaws.com:8080/auth  -- Desligado para não gastar o free tier. <br/>
API-STUDENTS: http://ec2-18-230-113-95.sa-east-1.compute.amazonaws.com:8083  -- Desligado para não ultrapassar o free tier <br/>

<br/>

<b>MODELAGEM DE CLASSES:</b><br/>
https://github.com/pucrs-poli/constr-sw-2021-1-g7/tree/alunos/src/main/resources

<br/>

<b>Collections utilizadas na API:</b>

//STUDENT<br/>
{ <br/>
  &nbsp;&nbsp;"idStudent": "ObjectId", <br/>
  &nbsp;&nbsp;"cpf": "string", <br/>
  &nbsp;&nbsp;"name": "string", <br/>
  &nbsp;&nbsp;"address": "string", <br/>
  &nbsp;&nbsp;"birthDate": "date" <br/>
} <br/>
<br/>
//SUBSCRIPTION <br/>
{ <br/>
    &nbsp;&nbsp;"idSubscription": "ObjectId", <br/>
    &nbsp;&nbsp;"code": "number", <br/>
    &nbsp;&nbsp;"idStudent": "ObjectId", <br/>
    &nbsp;&nbsp; "edition": "string", <br/>
    &nbsp;&nbsp;"tests": [ <br/>
    &nbsp;&nbsp;&nbsp;&nbsp;  "string", <br/>
    &nbsp;&nbsp;&nbsp;&nbsp;  "..." <br/>
    &nbsp;&nbsp;] <br/>
} <br/>

SWAGGER NO PROJETO: http://ec2-18-230-113-95.sa-east-1.compute.amazonaws.com:8083/swagger-ui.html# <br/>
SWAGGER YAML: https://github.com/pucrs-poli/constr-sw-2021-1-g7/tree/alunos/src/main/resources <br/>


<br/>

<b>MODELAGEM DE CLASSES:</b><br/>
https://github.com/pucrs-poli/constr-sw-2021-1-g7/tree/alunos/src/main/resources

<br/>

<b>Collections utilizadas na API:</b>

//STUDENT<br/>
{ <br/>
  &nbsp;&nbsp;"idStudent": "ObjectId", <br/>
  &nbsp;&nbsp;"cpf": "string", <br/>
  &nbsp;&nbsp;"name": "string", <br/>
  &nbsp;&nbsp;"address": "string", <br/>
  &nbsp;&nbsp;"birthDate": "date" <br/>
} <br/>
<br/>
//SUBSCRIPTION <br/>
{ <br/>
    &nbsp;&nbsp;"idSubscription": "ObjectId", <br/>
    &nbsp;&nbsp;"code": "number", <br/>
    &nbsp;&nbsp;"idStudent": "ObjectId", <br/>
    &nbsp;&nbsp; "edition": "string", <br/>
    &nbsp;&nbsp;"tests": [ <br/>
    &nbsp;&nbsp;&nbsp;&nbsp;  "string", <br/>
    &nbsp;&nbsp;&nbsp;&nbsp;  "..." <br/>
    &nbsp;&nbsp;] <br/>
} <br/>

SWAGGER NO PROJETO: http://ec2-18-230-113-95.sa-east-1.compute.amazonaws.com:8083/swagger-ui.html# <br/>
SWAGGER YAML: https://github.com/pucrs-poli/constr-sw-2021-1-g7/tree/alunos/src/main/resources <br/>
