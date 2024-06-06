import boto3
from datetime import datetime, timedelta
import time

def lambda_handler(event, context):
# Cria um cliente S3
logs_client = boto3.client('logs')
s3 = boto3.client('s3')
# Especifica o nome do bucket, o nome do arquivo no S3 e o caminho local do arquivo
bucket_name = 'inteli-nimbus-logs'
s3_file_name = 'logs.txt'

# Especifica o grupo de logs do CloudWatch e o intervalo de tempo
log_group_name = 'access_log'
end_time = int(datetime.now().timestamp() * 1000)  # Tempo atual em milissegundos
start_time = end_time - (365 * 24 * 60 * 60 * 1000)  # 365 dias antes do tempo atual em milissegundos


# Faz a consulta para coletar os logs
query = "fields @timestamp, @message | sort @timestamp desc | limit 20"
start_query_response = logs_client.start_query(
    logGroupName=log_group_name,
    startTime=start_time,
    endTime=end_time,
    queryString=query,
)

query_id = start_query_response['queryId']

# Aguardar até que a consulta seja concluída
response = None
while response is None or response['status'] == 'Running':
    time.sleep(1)  # Adiciona um delay para evitar excesso de requisições
    response = logs_client.get_query_results(queryId=query_id)

# Processar os resultados da consulta, se houver
log_lines = []
if 'results' in response and response['results']:
    for result in response['results']:
        timestamp = next(item['value'] for item in result if item['field'] == '@timestamp')
        message = next(item['value'] for item in result if item['field'] == '@message')
        log_lines.append(f"{timestamp} {message}")
else:
    log_lines.append("Nenhum log encontrado no intervalo especificado.")

log_content = "\\n".join(log_lines)

print(log_content)

# Caminho para o arquivo temporário no ambiente Lambda
local_file_path = '/tmp/teste.txt'

# Gerando um arquivo temporário
with open(local_file_path, 'w') as f:
    f.write(log_content)

# Faz o upload do arquivo para o bucket especificado
try:
    s3.upload_file(local_file_path, bucket_name, s3_file_name)
    return_message = f"Arquivo enviado com sucesso: {s3_file_name}"
except Exception as e:
    return_message = f"Erro ao fazer upload do arquivo: {e}"

return {
    'statusCode': 200,
    'body': return_message
}