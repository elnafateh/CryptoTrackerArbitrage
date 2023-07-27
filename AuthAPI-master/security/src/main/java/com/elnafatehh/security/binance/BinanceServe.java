package com.elnafatehh.security.binance;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.List;

@Service
public class BinanceServe {
    @Autowired
    private  BinanceAPI binanceAPI;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    //private PriceDataDAO priceDataDAO;
    @Value("${binance.api.key}")
    private  String apiKeys;

    @Value("${binance.api.secret}")
    private  String secretKeys;
//    @Autowired
//    public BinanceServe( @Qualifier("${binance.api.key}") BinanceAPI binanceAPI) {
//        this.binanceAPI = binanceAPI;
//    }

//    @Autowired
//    public BinanceServe(@Value("${persistence.unit.name}") String persistenceUnitName){
//    priceDataDAO= new PriceDataDAO(persistenceUnitName);
//    }
    // A method that gets the price of a symbol using the BinanceClient object
    public String getPrice(String symbol) throws IOException {
        // Call the getPrice method of the BinanceClient object and return the result
        String priceData= binanceAPI.getPrice(symbol);

        return priceData;
    }
    public PriceData getPriceData(String symbol)throws IOException{
        // Call the getPrice method of the BinanceClient object and get the price data as a string
        String priceData= binanceAPI.getPrice(symbol);

        // Use a JSON parser to get the price data as a JSON object
        JSONObject priceJson= new JSONObject(priceData);

        // Get the symbol and price fields from the JSON object
         symbol =priceJson.getString("symbol");
         Double price = priceJson.getDouble("price");

        // Create a PriceData object with the symbol and price
        PriceData priceDataObject = new PriceData(symbol,price);
        return priceDataObject;
    }
//    public void savePriceData(PriceData priceData){
//        priceDataDAO.insertPriceData(priceData);
//    }

//    public List<PriceData> getPriceDataBySymbol(String symbol) {
//        // Get an EntityManager object from the EntityManagerFactory
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        // Create a CriteriaBuilder object from the EntityManager
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//
//        // Create a CriteriaQuery object from the CriteriaBuilder
//        CriteriaQuery<PriceData> criteriaQuery = criteriaBuilder.createQuery(PriceData.class);
//
//        // Create a Root object from the CriteriaQuery
//        Root<PriceData> root = criteriaQuery.from(PriceData.class);
//
//        // Create a Predicate object from the CriteriaBuilder and the Root
//        Predicate predicate = criteriaBuilder.equal(root.get("symbol"), symbol);
//
//        // Set the Predicate object as the where clause of the CriteriaQuery
//        criteriaQuery.where(predicate);
//
//        // Create a TypedQuery object from the EntityManager and the CriteriaQuery
//        TypedQuery<PriceData> typedQuery = entityManager.createQuery(criteriaQuery);
//
//        // Execute the query and get the result list
//        List<PriceData> result = typedQuery.getResultList();
//
//        // Close the EntityManager
//        entityManager.close();
//
//        // Return the result list
//        return result;
//    }
//    public void close(){
//        priceDataDAO.close();
  //  }


}
