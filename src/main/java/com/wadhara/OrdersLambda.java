package com.wadhara;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.amazonaws.AmazonClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.amazonaws.services.kinesis.model.PutRecordResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wadhara.entity.Constatacao;
import com.wadhara.entity.ResponseSolicitacaoScore;
import com.wadhara.entity.SolicitacaoScore;

import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesis.KinesisClient;

public class OrdersLambda {

	public String handleRequest(KinesisEvent event, Context context)
			throws JsonMappingException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();

		List<ResponseSolicitacaoScore> listResp = new ArrayList();
		
		for (KinesisEvent.KinesisEventRecord record : event.getRecords()) {
			String data = StandardCharsets.UTF_8.decode(record.getKinesis().getData()).toString();

			SolicitacaoScore request = mapper.readValue(data, SolicitacaoScore.class);

			ResponseSolicitacaoScore resp = new ResponseSolicitacaoScore();
			resp.setNumeroNfe(request.getIdNota());
			resp.setIdTransacao(request.getIdSolicitacao());
			
			Constatacao constatacaoNova = new Constatacao();
			constatacaoNova.setConstatacao("Constatacao de Exemplo");
			List<Constatacao> listaConst = new ArrayList();
			listaConst.add(constatacaoNova);
			resp.setConstatacao(listaConst);
			
			listResp.add(resp);
			
		}
		
		enviaRecords(listResp);

		return "SUCCESS";

	}
	
	private void enviaRecords(List<ResponseSolicitacaoScore> resp) throws JsonProcessingException {
		
		AmazonKinesis kinesisClient = getKinesisClient();
		ObjectMapper mapper = new ObjectMapper();
		
		for(ResponseSolicitacaoScore score: resp) {
			
			String data = mapper.writeValueAsString(score);
			
			PutRecordRequest putRecord = new PutRecordRequest();
			putRecord.setStreamName("PDD_Mock_Score");
			putRecord.setPartitionKey(UUID.randomUUID().toString());
			putRecord.setData(ByteBuffer.wrap(data.toString().getBytes()));

			try {

				PutRecordResult result = kinesisClient.putRecord(putRecord);
				System.out.println("Mensagem enviada com sucesso.");
				System.out.println(result);
				System.out.println(result.getShardId());

			} catch (AmazonClientException ex) {
				System.out.println("Deu ruim: " + ex);
			}
			
		}
		
		
	}
	
	public static AmazonKinesis getKinesisClient() {
		
		System.setProperty("aws.accessKeyId", "AKIA2S2UJPPJQ2LKQNMG");
		System.setProperty("aws.secretAccessKey", "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");
		
		return AmazonKinesisClientBuilder
				.standard()
				.withRegion(Regions.US_EAST_1)
				.build();
	}
	
	
}
