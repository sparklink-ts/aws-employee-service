{{/* Define a helper to generate the full MySQL connection URL */}}
{{- define "employee-service.fullMySQLConnectionURL" -}}
jdbc:mysql://{{ .Release.Name }}-mysql-service:3306/{{ .Values.mysql.databaseName }}?createDatabaseIfNotExist=true&characterEncoding=UTF-8&verifyServerCertificate=false&useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true
/* Ryzen-3 Database */
/*jdbc:mysql://mysql-service:3306/{{ .Values.mysql.databaseName }}?createDatabaseIfNotExist=true&characterEncoding=UTF-8&verifyServerCertificate=false&useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true*/
{{- end -}}
