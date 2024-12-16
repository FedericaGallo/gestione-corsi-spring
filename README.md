## Contratto API

### Richiesta
* URI: /docente/getDocenteById/{id}
* verbo HTTP:GET
* Corpo: nessuno

### Risposta
```http
{
"id": 1,
"nome": "Federica",
"cognome": "Gallo",
"corsi": [
{
"id": 3,
"nomeCorso": "java",
"dataInizio": "2026/06/02",
"durata": "4"
},
{
"id": 5,
"nomeCorso": "francese",
"dataInizio": "2024/04/02",
"durata": "3"
}
]
```
### Richiesta
* URI: /docente/findAll
* verbo HTTP:GET
* Corpo: nessuno
### Risposta
```http
{
"id": 1,  
"nome": "Federica",  
"cognome": "Gallo",  
"corsi": [  
{
"id": 3,  
"nomeCorso": "java",  
"dataInizio": "2026/06/02",  
"durata": "4"  
},  
{
"id": 5,  
"nomeCorso": "francese",  
"dataInizio": "2024/04/02",  
"durata": "3"  
}
]
"id": 2,  
"nome": "Mario",  
"cognome": "Rossi",  
"corsi": []
}
```
### Richiesta
* URI: /docente/addDocente
* verbo HTTP:POST
* Corpo: 
```http
{
    "nome": "Gianni",
    "cognome": "Morandi"
}
```
### Risposta
```http
{
    "id": 8,
    "nome": "Gianni",
    "cognome": "Morandi"
}
```
### Richiesta
* URI: /docente/deleteDocente/{id}
* verbo HTTP:DELETE
* Corpo: nessuno
### Risposta

### Richiesta
* URI: /corso/addCorso
* verbo HTTP:POST
* Corpo: 
```http
{
    "nomeCorso":"spagnolo",
    "dataInizio": "2023/09/07",
    "durata":"4 masi",
    "idDocenteDTO": "1"
}
```
### Risposta

### Richiesta
* URI: /corso/updateCorso/{id}
* verbo HTTP:PUT
* Corpo:
```http
{
    "nomeCorso":"spagnolo",
    "dataInizio": "2023/09/07",
    "durata":"4 masi",
    "idDocenteDTO": "1"
}
```
### Risposta
```http
{
    "id": 21,
    "nomeCorso": "spagnolo",
    "dataInizio": "2021/04/30",
    "durata": "4 masi",
    "idDocenteDTO": 2
}
```
### Richiesta
* URI: /discente/addDiscente/
* verbo HTTP:POST
* Corpo:
```http
{
    "nome":"Federica",
    "cognome": "Gallo",
    "matricola":"SH400",
    "dataDiNascita": "1993/08/08"
}
```
### Risposta
```http
{
    "id": 13,
    "nome": "Federica",
    "cognome": "Gallo",
    "matricola": "SH400",
    "dataDiNascita": "1993/08/08",
    "corsiSeguiti": []
}
```