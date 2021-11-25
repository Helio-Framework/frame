package helio.framework;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


public class FrameworkUtils {
	
	public static final Gson GSON = new Gson();
	public static final ObjectMapper JACKSON_MAPPER = new ObjectMapper();
	public static Integer deafultQOS = 0;
	static{
		JACKSON_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JACKSON_MAPPER.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
	}
	
	public static String buildMessage(String ... values) {
		StringBuilder message = new StringBuilder();
		for(int index=0; index < values.length; index++) {
			message.append(values[index]);
		}
		return message.toString();
	}

	public static String concatenate(String ... values) {
		StringBuilder str = new StringBuilder();
		
		for(int index=0; index < values.length; index++) {
			str.append(values[index]);
		}
		return str.toString();
	}
	
	public static MqttMessage toMqttMessage(String payload, Integer qos) {
		MqttMessage message = new MqttMessage(payload.getBytes());
		message.setQos(qos);
		return message;
	}
	
	public static MqttMessage toMqttMessage(String payload) {
		MqttMessage message = new MqttMessage(payload.getBytes());
		message.setQos(deafultQOS);
		return message;
	}

}
