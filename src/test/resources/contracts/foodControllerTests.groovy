//package contracts
//
//import org.springframework.cloud.contract.spec.Contract;
//
//[
//        Contract.make {
//            name "Food Controller Test get food"
//            request {
//                method GET()
//                url("http://localhost:8080/calorie-meter/food/getFood") {
//                    queryParameters {
//                        parameter("id", "1")
//                    }
//                }
//
//            }
//            response {
//                body(
//                       "id": 1,
//                        "name": "Гречка",
//                        "calories":  100,
//                        "level": "LOW"
//                )
//                status 200
//            }
//        }
//]
//
