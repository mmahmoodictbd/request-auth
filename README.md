# How to run:
1. Download Activator from https://downloads.typesafe.com/typesafe-activator/1.3.10/typesafe-activator-1.3.10-minimal.zip
2. Create a DB named advauth and run $ activator run
3. curl http://localhost:9000/oauth/access_token -X POST -d "client_id=bob_client_id" -d "client_secret=bob_client_secret" -d "grant_type=client_credentials"
