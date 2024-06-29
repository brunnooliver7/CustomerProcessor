docker stop sql_server
docker rm sql_server
docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=myPass123!' -p 1433:1433 --name sql_server -d mcr.microsoft.com/mssql/server:2019-latest