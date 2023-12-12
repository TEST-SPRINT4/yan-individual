import wmi
import mysql.connector
import time
from datetime import datetime



connection = mysql.connector.connect(
    host='localhost',
    user='root',
    password='yanyan',
    database='testIndividual'
)

cursor = connection.cursor()
   
def get_cpu_temperature():
    try:
        # Conecta ao serviço WMI
        w = wmi.WMI(namespace="root/OpenHardwareMonitor")

        # Obtém informações sobre sensores
        temperature_info = w.Sensor()

        # Procura pela temperatura da CPU
        for sensor in temperature_info:
            if sensor.SensorType == 'Temperature' and 'CPU' in sensor.Name:
                return sensor.Value

        return None

    except Exception as e:
        print(f"Erro ao obter temperatura da CPU: {e}")
        return None

# Obtém e imprime a temperatura da CPU
while True:
    cpu_temperature = get_cpu_temperature()
    if cpu_temperature is not None:
       sql_query_temperatura = "INSERT INTO RegistrosTRUSTED (dadosCapturados, dataHora, fkComponente, fkIdservidor) VALUES (%s, now(), %s, %s)"
       values_temperatura = (cpu_temperature, 12, 1)

    else:
        print("Não foi possível obter a temperatura da CPU.")

    try:
            # Executa a inserção
            # cursor.execute(sql2, values2)
            cursor.execute(sql_query_temperatura, values_temperatura)
            # Confirma as alterações no banco de dados
            connection.commit()
            print("Inserção de dados realizada com sucesso!")

    except mysql.connector.Error as err:
        print("Erro ao inserir nas tabelas Registros:", err)
    time.sleep(5)

