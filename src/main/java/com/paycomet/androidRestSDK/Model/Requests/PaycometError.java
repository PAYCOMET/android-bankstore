package com.paycomet.androidRestSDK.Model.Requests;

import static com.paycomet.androidRestSDK.Utils.Constants.ERROR_DESCRIPTION;
import static com.paycomet.androidRestSDK.Utils.Constants.ERROR_ID;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PaycometError {

    /**
     * The ID of the error
     */
    @SerializedName(ERROR_ID)
    private String errorId;

    @SerializedName(ERROR_DESCRIPTION)
    private String errorDescription;

    public String getErrorId() {
        return errorId;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public PaycometError() { }

    /**
     * Creates a new PaycometError.
     *
     * @param errorId String representation of the error's ID.
     */
    public PaycometError(String errorId) {
        this.errorId = errorId;
    }

    /**
     * Converts the error ID into a message.
     *
     * @return The error message.
     */
    public String getError() throws Exception {
        String message = "";

        if (this.errorId == null) {
            throw new ArithmeticException("Null error id");
        }

        if (!isInteger(this.errorId, 10)) {
            return this.errorId;
        } else {
            int error = Integer.parseInt(errorId);

            switch (error) {
                case 1:
                    message = "Error";
                    break;

                case 100:
                    message = "Expired credit card";
                    break;

                case 101:
                    message = "Credit card blacklisted";
                    break;

                case 102:
                    message = "Operation not allowed for the credit card type";
                    break;

                case 103:
                    message = "Please, call the credit card issuer";
                    break;

                case 104:
                    message = "Unexpected error";
                    break;

                case 105:
                    message = "Insufficient funds";
                    break;

                case 106:
                    message = "Credit card not registered or not logged by the issuer";
                    break;

                case 107:
                    message = "Data error. Validation Code";
                    break;

                case 108:
                    message = "PAN Check Error";
                    break;

                case 109:
                    message = "Expiry date error";
                    break;

                case 110:
                    message = "Data error";
                    break;

                case 111:
                    message = "CVC2 block incorrect";
                    break;

                case 112:
                    message = "Please, call the credit card issuer";
                    break;

                case 113:
                    message = "Credit card not valid";
                    break;

                case 114:
                    message = "The credit card has credit restrictions";
                    break;

                case 115:
                    message = "Card issuer could not validate card owner";
                    break;

                case 116:
                    message = "Payment not allowed in off-line authorization";
                    break;

                case 118:
                    message = "Expired credit card. Please capture card";
                    break;

                case 119:
                    message = "Credit card blacklisted. Please capture card";
                    break;

                case 120:
                    message = "Credit card lost or stolen. Please capture card";
                    break;

                case 121:
                    message = "Error in CVC2. Please capture card";
                    break;

                case 122:
                    message = "Error en Pre-Transaction process. Try again later.";
                    break;

                case 123:
                    message = "Operation denied. Please capture card";
                    break;

                case 124:
                    message = "Closing with agreement";
                    break;

                case 125:
                    message = "Closing without agreement";
                    break;

                case 126:
                    message = "Cannot close right now";
                    break;

                case 127:
                    message = "Invalid parameter";
                    break;

                case 128:
                    message = "Transactions were not accomplished";
                    break;

                case 129:
                    message = "Duplicated internal reference";
                    break;

                case 130:
                    message = "Original operation not found. Could not refund";
                    break;

                case 131:
                    message = "Expired preauthorization";
                    break;

                case 132:
                    message = "Operation not valid with selected currency";
                    break;

                case 133:
                    message = "Error in message format";
                    break;

                case 134:
                    message = "Message not recognized by the system";
                    break;

                case 135:
                    message = "CVC2 block incorrect";
                    break;

                case 137:
                    message = "Credit card not valid";
                    break;

                case 138:
                    message = "Gateway message error";
                    break;

                case 139:
                    message = "Gateway format error";
                    break;

                case 140:
                    message = "Credit card does not exist";
                    break;

                case 141:
                    message = "Amount zero or not valid";
                    break;

                case 142:
                    message = "Operation canceled";
                    break;

                case 143:
                    message = "Authentification error";
                    break;

                case 144:
                    message = "Denegation by security level";
                    break;

                case 145:
                    message = "Error in PUC message. Please contact PAYTPV";
                    break;

                case 146:
                    message = "System error";
                    break;

                case 147:
                    message = "Duplicated transaction";
                    break;

                case 148:
                    message = "MAC error";
                    break;

                case 149:
                    message = "Settlement rejected";
                    break;

                case 150:
                    message = "System date/time not synchronized";
                    break;

                case 151:
                    message = "Invalid card expiration date";
                    break;

                case 152:
                    message = "Could not find any preauthorization with given data";
                    break;

                case 153:
                    message = "Cannot find requested data";
                    break;

                case 154:
                    message = "Cannot operate with given credit card";
                    break;

                case 155:
                    message = "This method requires activation of the VHASH protocol";
                    break;

                case 500:
                    message = "Unexpected error";
                    break;

                case 501:
                    message = "Unexpected error";
                    break;

                case 502:
                    message = "Unexpected error";
                    break;

                case 504:
                    message = "Transaction already cancelled";
                    break;

                case 505:
                    message = "Transaction originally denied";
                    break;

                case 506:
                    message = "Confirmation data not valid";
                    break;

                case 507:
                    message = "Unexpected error";
                    break;

                case 508:
                    message = "Transaction still in process";
                    break;

                case 509:
                    message = "Unexpected error";
                    break;

                case 510:
                    message = "Refund is not possible";
                    break;

                case 511:
                    message = "Unexpected error";
                    break;

                case 512:
                    message = "Card issuer not available right now. Please try again later";
                    break;

                case 513:
                    message = "Unexpected error";
                    break;

                case 514:
                    message = "Unexpected error";
                    break;

                case 515:
                    message = "Unexpected error";
                    break;

                case 516:
                    message = "Unexpected error";
                    break;

                case 517:
                    message = "Unexpected error";
                    break;

                case 518:
                    message = "Unexpected error";
                    break;

                case 519:
                    message = "Unexpected error";
                    break;

                case 520:
                    message = "Unexpected error";
                    break;

                case 521:
                    message = "Unexpected error";
                    break;

                case 522:
                    message = "Unexpected error";
                    break;

                case 523:
                    message = "Unexpected error";
                    break;

                case 524:
                    message = "Unexpected error";
                    break;

                case 525:
                    message = "Unexpected error";
                    break;

                case 526:
                    message = "Unexpected error";
                    break;

                case 527:
                    message = "TransactionType desconocido";
                    break;

                case 528:
                    message = "Unexpected error";
                    break;

                case 529:
                    message = "Unexpected error";
                    break;

                case 530:
                    message = "Unexpected error";
                    break;

                case 531:
                    message = "Unexpected error";
                    break;

                case 532:
                    message = "Unexpected error";
                    break;

                case 533:
                    message = "Unexpected error";
                    break;

                case 534:
                    message = "Unexpected error";
                    break;

                case 535:
                    message = "Unexpected error";
                    break;

                case 536:
                    message = "Unexpected error";
                    break;

                case 537:
                    message = "Unexpected error";
                    break;

                case 538:
                    message = "Not cancelable operation";
                    break;

                case 539:
                    message = "Unexpected error";
                    break;

                case 540:
                    message = "Unexpected error";
                    break;

                case 541:
                    message = "Unexpected error";
                    break;

                case 542:
                    message = "Unexpected error";
                    break;

                case 543:
                    message = "Unexpected error";
                    break;

                case 544:
                    message = "Unexpected error";
                    break;

                case 545:
                    message = "Unexpected error";
                    break;

                case 546:
                    message = "Unexpected error";
                    break;

                case 547:
                    message = "Unexpected error";
                    break;

                case 548:
                    message = "Unexpected error";
                    break;

                case 549:
                    message = "Unexpected error";
                    break;

                case 550:
                    message = "Unexpected error";
                    break;

                case 551:
                    message = "Unexpected error";
                    break;

                case 552:
                    message = "Unexpected error";
                    break;

                case 553:
                    message = "Unexpected error";
                    break;

                case 554:
                    message = "Unexpected error";
                    break;

                case 555:
                    message = "Could not find the previous operation";
                    break;

                case 556:
                    message = "Data inconsistency in cancellation validation";
                    break;

                case 557:
                    message = "Delayed payment code does not exists";
                    break;

                case 558:
                    message = "Unexpected error";
                    break;

                case 559:
                    message = "Unexpected error";
                    break;

                case 560:
                    message = "Unexpected error";
                    break;

                case 561:
                    message = "Unexpected error";
                    break;

                case 562:
                    message = "Credit card does not allow preauthorizations";
                    break;

                case 563:
                    message = "Data inconsistency in confirmation";
                    break;

                case 564:
                    message = "Unexpected error";
                    break;

                case 565:
                    message = "Unexpected error";
                    break;

                case 567:
                    message = "Refund operation not correctly specified";
                    break;

                case 568:
                    message = "Online communication incorrect";
                    break;

                case 569:
                    message = "Denied operation";
                    break;

                case 1000:
                    message = "Account not found. Review your settings";
                    break;

                case 1001:
                    message = "User not found. Please contact your administrator";
                    break;

                case 1002:
                    message = "External provider signature error. Contact your service provider";
                    break;

                case 1003:
                    message = "Signature not valid. Please review your settings";
                    break;

                case 1004:
                    message = "Forbidden access";
                    break;

                case 1005:
                    message = "Invalid credit card format";
                    break;

                case 1006:
                    message = "Data error: Validation code";
                    break;

                case 1007:
                    message = "Data error: Expiration date";
                    break;

                case 1008:
                    message = "Preauthorization reference not found";
                    break;

                case 1009:
                    message = "Preauthorization data could not be found";
                    break;

                case 1010:
                    message = "Could not send cancellation. Please try again later";
                    break;

                case 1011:
                    message = "Could not connect to host";
                    break;

                case 1012:
                    message = "Could not resolve proxy address";
                    break;

                case 1013:
                    message = "Could not resolve host";
                    break;

                case 1014:
                    message = "Initialization failed";
                    break;

                case 1015:
                    message = "Could not find HTTP resource";
                    break;

                case 1016:
                    message = "The HTTP options range is not valid";
                    break;

                case 1017:
                    message = "The POST is not correctly built";
                    break;

                case 1018:
                    message = "The username is not correctly formatted";
                    break;

                case 1019:
                    message = "Operation timeout exceeded";
                    break;

                case 1020:
                    message = "Insufficient memory";
                    break;

                case 1021:
                    message = "Could not connect to SSL host";
                    break;

                case 1022:
                    message = "Protocol not supported";
                    break;

                case 1023:
                    message = "Given URL is not correctly formatted and cannot be used";
                    break;

                case 1024:
                    message = "URL user is not correctly formatted";
                    break;

                case 1025:
                    message = "Cannot register available resources to complete current operation";
                    break;

                case 1026:
                    message = "Duplicated external reference";
                    break;

                case 1027:
                    message = "Total refunds cannot exceed original payment";
                    break;

                case 1028:
                    message = "Account not active. Please contact PAYTPV";
                    break;

                case 1029:
                    message = "Account still not certified. Please contact PAYTPV";
                    break;

                case 1030:
                    message = "Product is marked for deletion and cannot be used";
                    break;

                case 1031:
                    message = "Insufficient rights";
                    break;

                case 1032:
                    message = "Product cannot be used under test environment";
                    break;

                case 1033:
                    message = "Product cannot be used under production environment";
                    break;

                case 1034:
                    message = "It was not possible to send the refund request";
                    break;

                case 1035:
                    message = "Error in field operation origin IP";
                    break;

                case 1036:
                    message = "Error in XML format";
                    break;

                case 1037:
                    message = "Root element is not correct";
                    break;

                case 1038:
                    message = "Field DS_MERCHANT_AMOUNT incorrect";
                    break;

                case 1039:
                    message = "Field DS_MERCHANT_ORDER incorrect";
                    break;

                case 1040:
                    message = "Field DS_MERCHANT_MERCHANTCODE incorrect";
                    break;

                case 1041:
                    message = "Field DS_MERCHANT_CURRENCY incorrect";
                    break;

                case 1042:
                    message = "Field DS_MERCHANT_PAN incorrect";
                    break;

                case 1043:
                    message = "Field DS_MERCHANT_CVV2 incorrect";
                    break;

                case 1044:
                    message = "Field DS_MERCHANT_TRANSACTIONTYPE incorrect";
                    break;

                case 1045:
                    message = "Field DS_MERCHANT_TERMINAL incorrect";
                    break;

                case 1046:
                    message = "Field DS_MERCHANT_EXPIRYDATE incorrect";
                    break;

                case 1047:
                    message = "Field DS_MERCHANT_MERCHANTSIGNATURE incorrect";
                    break;

                case 1048:
                    message = "Field DS_ORIGINAL_IP incorrect";
                    break;

                case 1049:
                    message = "Client not found";
                    break;

                case 1050:
                    message = "Preauthorization amount cannot be greater than previous preauthorization amount";
                    break;

                case 1099:
                    message = "Unexpected error";
                    break;

                case 1100:
                    message = "Card diary limit exceeds";
                    break;

                case 1103:
                    message = "ACCOUNT field error";
                    break;

                case 1104:
                    message = "USERCODE field error";
                    break;

                case 1105:
                    message = "TERMINAL field error";
                    break;

                case 1106:
                    message = "OPERATION field error";
                    break;

                case 1107:
                    message = "REFERENCE field error";
                    break;

                case 1108:
                    message = "AMOUNT field error";
                    break;

                case 1109:
                    message = "CURRENCY field error";
                    break;

                case 1110:
                    message = "SIGNATURE field error";
                    break;

                case 1120:
                    message = "Operation unavailable";
                    break;

                case 1121:
                    message = "Client not found";
                    break;

                case 1122:
                    message = "User not found. Contact PAYTPV";
                    break;

                case 1123:
                    message = "Invalid signature. Please check your configuration";
                    break;

                case 1124:
                    message = "Operation not available with the specified user";
                    break;

                case 1125:
                    message = "Invalid operation in a currency other than Euro";
                    break;

                case 1127:
                    message = "Quantity zero or invalid";
                    break;

                case 1128:
                    message = "Current currency conversion invalid";
                    break;

                case 1129:
                    message = "Invalid amount";
                    break;

                case 1130:
                    message = "Product not found";
                    break;

                case 1131:
                    message = "Invalid operation with the current currency";
                    break;

                case 1132:
                    message = "Invalid operation with a different article of the Euro currency";
                    break;

                case 1133:
                    message = "Info button corrupt";
                    break;

                case 1134:
                    message = "The subscription may not exceed the expiration date of the card";
                    break;

                case 1135:
                    message = "DS_EXECUTE can not be true if DS_SUBSCRIPTION_STARTDATE is different from today.";
                    break;

                case 1136:
                    message = "PAYTPV_OPERATIONS_MERCHANTCODE field error";
                    break;

                case 1137:
                    message = "PAYTPV_OPERATIONS_TERMINAL must be Array";
                    break;

                case 1138:
                    message = "PAYTPV_OPERATIONS_OPERATIONS must be Array";
                    break;

                case 1139:
                    message = "PAYTPV_OPERATIONS_SIGNATURE field error";
                    break;

                case 1140:
                    message = "Can not find any of the PAYTPV_OPERATIONS_TERMINAL";
                    break;

                case 1141:
                    message = "Error in the date range requested";
                    break;

                case 1142:
                    message = "The application can not have a length greater than 2 years";
                    break;

                case 1143:
                    message = "The operation state is incorrect";
                    break;

                case 1144:
                    message = "Error in the amounts of the search";
                    break;

                case 1145:
                    message = "The type of operation requested does not exist";
                    break;

                case 1146:
                    message = "Sort Order unrecognized";
                    break;

                case 1147:
                    message = "PAYTPV_OPERATIONS_SORTORDER unrecognized";
                    break;

                case 1148:
                    message = "Subscription start date wrong";
                    break;

                case 1149:
                    message = "Subscription end date wrong";
                    break;

                case 1150:
                    message = "Frequency error in the subscription";
                    break;

                case 1151:
                    message = "Invalid usuarioXML ";
                    break;

                case 1152:
                    message = "Invalid codigoCliente";
                    break;

                case 1153:
                    message = "Invalid usuarios parameter";
                    break;

                case 1154:
                    message = "Invalid firma parameter";
                    break;

                case 1155:
                    message = "Invalid usuarios parameter format";
                    break;

                case 1156:
                    message = "Invalid type";
                    break;

                case 1157:
                    message = "Invalid name";
                    break;

                case 1158:
                    message = "Invalid surname";
                    break;

                case 1159:
                    message = "Invalid email";
                    break;

                case 1160:
                    message = "Invalid password";
                    break;

                case 1161:
                    message = "Invalid language";
                    break;

                case 1162:
                    message = "Invalid maxamount";
                    break;

                case 1163:
                    message = "Invalid multicurrency";
                    break;

                case 1165:
                    message = "Invalid permissions_specs. Format not allowed";
                    break;

                case 1166:
                    message = "Invalid permissions_products. Format not allowed";
                    break;

                case 1167:
                    message = "Invalid email. Format not allowed";
                    break;

                case 1168:
                    message = "Weak or invalid password";
                    break;

                case 1169:
                    message = "Invalid value for type parameter";
                    break;

                case 1170:
                    message = "Invalid value for language parameter";
                    break;

                case 1171:
                    message = "Invalid format for maxamount parameter";
                    break;

                case 1172:
                    message = "Invalid multicurrency. Format not allowed";
                    break;

                case 1173:
                    message = "Invalid permission_id – permissions_specs. Not allowed";
                    break;

                case 1174:
                    message = "Invalid user";
                    break;

                case 1175:
                    message = "Invalid credentials";
                    break;

                case 1176:
                    message = "Account not found";
                    break;

                case 1177:
                    message = "User not found";
                    break;

                case 1178:
                    message = "Invalid signature";
                    break;

                case 1179:
                    message = "Account without products";
                    break;

                case 1180:
                    message = "Invalid product_id - permissions_products. Not allowed";
                    break;

                case 1181:
                    message = "Invalid permission_id -permissions_products. Not allowed";
                    break;

                case 1185:
                    message = "Minimun limit not allowed";
                    break;

                case 1186:
                    message = "Maximun limit not allowed";
                    break;

                case 1187:
                    message = "Daily limit not allowed";
                    break;

                case 1188:
                    message = "Monthly limit not allowed";
                    break;

                case 1189:
                    message = "Max amount (same card / last 24 h. not allowed";
                    break;

                case 1190:
                    message = "Max amount (same card / last 24 h. / same IP address not allowed";
                    break;

                case 1191:
                    message = "Day / IP address limit (all cards not allowed";
                    break;

                case 1192:
                    message = "Country (merchant IP address not allowed";
                    break;

                case 1193:
                    message = "Card type (credit / debit not allowed";
                    break;

                case 1194:
                    message = "Card brand not allowed";
                    break;

                case 1195:
                    message = "Card Category not allowed";
                    break;

                case 1196:
                    message = "Authorization from different country than card issuer, not allowed";
                    break;

                case 1197:
                    message = "Denied. Filter: Card country issuer not allowed";
                    break;

                case 1198:
                    message = "Scoring limit exceeded";
                    break;

                case 1200:
                    message = "Denied. Filter: same card, different country last 24 h.";
                    break;

                case 1201:
                    message = "Number of erroneous consecutive attempts with the same card exceeded";
                    break;

                case 1202:
                    message = "Number of failed attempts (last 30 minutes from the same ip address exceeded";
                    break;

                case 1203:
                    message = "Wrong or not configured PayPal credentials";
                    break;

                case 1204:
                    message = "Wrong token received";
                    break;

                case 1205:
                    message = "Can not perform the operation";
                    break;

                case 1206:
                    message = "ProviderID not available";
                    break;

                case 1207:
                    message = "Operations parameter missing or not in a correct format";
                    break;

                case 1208:
                    message = "PaytpvMerchant parameter missing";
                    break;

                case 1209:
                    message = "MerchatID parameter missing";
                    break;

                case 1210:
                    message = "TerminalID parameter missing";
                    break;

                case 1211:
                    message = "TpvID parameter missing";
                    break;

                case 1212:
                    message = "OperationType parameter missing";
                    break;

                case 1213:
                    message = "OperationResult parameter missing";
                    break;

                case 1214:
                    message = "OperationAmount parameter missing";
                    break;

                case 1215:
                    message = "OperationCurrency parameter missing";
                    break;

                case 1216:
                    message = "OperationDatetime parameter missing";
                    break;

                case 1217:
                    message = "OriginalAmount parameter missing";
                    break;

                case 1218:
                    message = "Pan parameter missing";
                    break;

                case 1219:
                    message = "ExpiryDate parameter missing";
                    break;

                case 1220:
                    message = "Reference parameter missing";
                    break;

                case 1221:
                    message = "Signature parameter missing";
                    break;

                case 1222:
                    message = "OriginalIP parameter missing or not in a correct format";
                    break;

                case 1223:
                    message = "Authcode / errorCode parameter missing";
                    break;

                case 1224:
                    message = "Product of the operation missing";
                    break;

                case 1225:
                    message = "The type of operation is not supported";
                    break;

                case 1226:
                    message = "The result of the operation is not supported";
                    break;

                case 1227:
                    message = "The transaction currency is not supported";
                    break;

                case 1228:
                    message = "The date of the transaction is not in a correct format";
                    break;

                case 1229:
                    message = "The signature is not correct";
                    break;

                case 1230:
                    message = "Can not find the associated account information";
                    break;

                case 1231:
                    message = "Can not find the associated product information";
                    break;

                case 1232:
                    message = "Can not find the associated user information";
                    break;

                case 1233:
                    message = "The product is not set as multicurrency";
                    break;

                case 1234:
                    message = "The amount of the transaction is not in a correct format";
                    break;

                case 1235:
                    message = "The original amount of the transaction is not in a correct format";
                    break;

                case 1236:
                    message = "The card does not have the correct format";
                    break;

                case 1237:
                    message = "The expiry date of the card is not in a correct format";
                    break;

                case 1238:
                    message = "Can not initialize the service";
                    break;

                case 1239:
                    message = "Can not initialize the service";
                    break;

                case 1240:
                    message = "Method not implemented";
                    break;

                case 1241:
                    message = "Can not initialize the service";
                    break;

                case 1242:
                    message = "Service can not be completed";
                    break;

                case 1243:
                    message = "OperationCode parameter missing";
                    break;

                case 1244:
                    message = "bankName parameter missing";
                    break;

                case 1245:
                    message = "csb parameter missing";
                    break;

                case 1246:
                    message = "userReference parameter missing";
                    break;

                case 1247:
                    message = "Can not find the associated FUC";
                    break;

                case 1248:
                    message = "Duplicate xref. Pending operation.";
                    break;

                case 1249:
                    message = "[DS_]AGENT_FEE parameter missing";
                    break;

                case 1250:
                    message = "[DS_]AGENT_FEE parameter is not in a correct format";
                    break;

                case 1251:
                    message = "DS_AGENT_FEE parameter is not correct";
                    break;

                case 1252:
                    message = "CANCEL_URL parameter missing";
                    break;

                case 1253:
                    message = "CANCEL_URL parameter is not in a correct format";
                    break;

                case 1254:
                    message = "Commerce with secure cardholder and cardholder without secure purchase key";
                    break;

                case 1255:
                    message = "Call terminated by the client";
                    break;

                case 1256:
                    message = "Call terminated, incorrect attempts exceeded";
                    break;

                case 1257:
                    message = "Call terminated, operation attempts exceeded";
                    break;

                case 1258:
                    message = "stationID not available";
                    break;

                case 1259:
                    message = "It has not been possible to establish the IVR session";
                    break;

                case 1260:
                    message = "merchantCode parameter missing";
                    break;

                case 1261:
                    message = "The merchantCode parameter is incorrect";
                    break;

                case 1262:
                    message = "terminalIDDebtor parameter missing";
                    break;

                case 1263:
                    message = " terminalIDCreditor parameter missing";
                    break;

                case 1264:
                    message = "Authorisations for carrying out the operation not available ";
                    break;

                case 1265:
                    message = "The Iban account (terminalIDDebtor is invalid";
                    break;

                case 1266:
                    message = "The Iban account (terminalIDCreditor is invalid";
                    break;

                case 1267:
                    message = "The BicCode of the Iban account (terminalIDDebtor is invalid";
                    break;

                case 1268:
                    message = "The BicCode of the Iban account (terminalIDCreditor is invalid";
                    break;

                case 1269:
                    message = "operationOrder parameter missing";
                    break;

                case 1270:
                    message = "The operationOrder parameter does not have the correct format";
                    break;

                case 1271:
                    message = "The operationAmount parameter does not have the correct format";
                    break;

                case 1272:
                    message = "The operationDatetime parameter does not have the correct format";
                    break;

                case 1273:
                    message = "The operationConcept parameter contains invalid characters or exceeds 140 characters";
                    break;

                case 1274:
                    message = "It has not been possible to record the SEPA operation";
                    break;

                case 1275:
                    message = "It has not been possible to record the SEPA operation";
                    break;

                case 1276:
                    message = "Can not create an operation token";
                    break;

                case 1277:
                    message = "Invalid scoring value";
                    break;

                case 1278:
                    message = "The language parameter is not in a correct format";
                    break;

                case 1279:
                    message = "The cardholder name is not in a correct format";
                    break;

                case 1280:
                    message = "The card does not have the correct format";
                    break;

                case 1281:
                    message = "The month does not have the correct format";
                    break;

                case 1282:
                    message = "The year does not have the correct format";
                    break;

                case 1283:
                    message = "The cvv2 does not have the correct format";
                    break;

                case 1284:
                    message = "The apiID parameter is not in a correct format";
                    break;

                case 1288:
                    message = "The splitId parameter is not valid";
                    break;

                case 1289:
                    message = "The splitId parameter is not allowed";
                    break;

                case 1290:
                    message = "This terminal don't allow split transfers";
                    break;

                case 1291:
                    message = "It has not been possible to record the split transfer operation";
                    break;

                case 1292:
                    message = "Original payment's date cannot exceed 90 days";
                    break;

                case 1293:
                    message = "Original split tansfer not found";
                    break;

                case 1294:
                    message = "Total reversal cannot exceed original split transfer";
                    break;

                case 1295:
                    message = "It has not been possible to record the split transfer reversal operation";
                    break;

                default:
                    message = "There was an unexpected error. Please try again later";
                    break;
            }
        }

        return message;
    }

    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty())
            return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1)
                    return false;
                else
                    continue;
            }
            if (Character.digit(s.charAt(i), radix) < 0)
                return false;
        }
        return true;
    }
}
