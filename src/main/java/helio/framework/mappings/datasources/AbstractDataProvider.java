package helio.framework.mappings.datasources;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import helio.framework.FrameworkUtils;

public abstract class AbstractDataProvider implements DataProvider{

	private static final long serialVersionUID = 6083285785158086619L;
	protected IMqttClient client;
	protected String topic;
	
	
	public void setClient(IMqttClient client, String topic) {
			this.client = client;
			this.topic = topic;
	}
	
	
	
	protected void sendData(String data) {
		try {
			client.publish(topic, FrameworkUtils.toMqttMessage(data));
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	
}
