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