package WEEK1.task7;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

 class PaymentRequest {

    private String paymentId;
    private String customerName;
    private Double amount;
    private String paymentMode;
    private String couponCode;
    private String bankName;
    private String walletName;

    public PaymentRequest(String paymentId,String customerName,Double amount,String paymentMode,String couponCode,String bankName,String walletName){
        this.paymentId=paymentId;
        this.customerName=customerName;
        this.amount=amount;
        this.paymentMode=paymentMode;
        this.couponCode=couponCode;
        this.bankName=bankName;
        this.walletName=walletName;
    }

    public String getPaymentId(){return paymentId;}
    public String getCustomerName(){return customerName;}
    public Double getAmount(){return amount;}
    public void setAmount(Double amount){this.amount=amount;}
    public String getPaymentMode(){return paymentMode;}
    public String getCouponCode(){return couponCode;}
    public String getBankName(){return bankName;}
    public String getWalletName(){return walletName;}
}

class PaymentResponse {

    private String transactionId;
    private String paymentStatus;
    private Double finalAmount;
    private String message;

    public PaymentResponse(String transactionId,String paymentStatus,Double finalAmount,String message){
        this.transactionId=transactionId;
        this.paymentStatus=paymentStatus;
        this.finalAmount=finalAmount;
        this.message=message;
    }

    public String getTransactionId(){return transactionId;}
    public String getPaymentStatus(){return paymentStatus;}
    public Double getFinalAmount(){return finalAmount;}
    public String getMessage(){return message;}
}

public interface PaymentGateway {
    PaymentResponse pay(PaymentRequest request);
}

class PaymentService {

    Predicate<PaymentRequest> validate=r->r.getAmount()>0;

    Function<PaymentRequest,PaymentRequest> applyCoupon=r->{
        if("COURSE10".equalsIgnoreCase(r.getCouponCode()))
            r.setAmount(r.getAmount()-r.getAmount()*0.10);
        return r;
    };

    Supplier<String> txn=()->"TXN"+UUID.randomUUID().toString().substring(0,8);

    PaymentGateway upi=r->{
        r.setAmount(r.getAmount()+10);
        return new PaymentResponse(txn.get(),"SUCCESS",r.getAmount(),"UPI Payment Success");
    };

    PaymentGateway card=r->{
        r.setAmount(r.getAmount()+25);
        return new PaymentResponse(txn.get(),"SUCCESS",r.getAmount(),"Card Payment Success");
    };

    PaymentGateway netBanking=r->{
        r.setAmount(r.getAmount()+15);
        return new PaymentResponse(txn.get(),"SUCCESS",r.getAmount(),"Net Banking Success");
    };

    PaymentGateway wallet=r->{
        r.setAmount(r.getAmount()+5);
        return new PaymentResponse(txn.get(),"SUCCESS",r.getAmount(),"Wallet Payment Success");
    };

    Map<String,PaymentGateway> gateways=new HashMap<>();

    public PaymentService(){
        gateways.put("UPI",upi);
        gateways.put("CARD",card);
        gateways.put("NETBANKING",netBanking);
        gateways.put("WALLET",wallet);
    }

    public PaymentResponse process(PaymentRequest request){

        if(!validate.test(request))
            throw new RuntimeException("Invalid Amount");

        request=applyCoupon.apply(request);

        PaymentGateway gateway=
                gateways.get(
                        request.getPaymentMode().toUpperCase()
                );

        return gateway.pay(request);
    }
}

class Test {

    public static void main(String[] args) {

        PaymentRequest request=new PaymentRequest("P101","Sai",25000.0,"UPI","COURSE10","SBI","Paytm");

        PaymentService service=new PaymentService();

        PaymentResponse response=service.process(request);

        System.out.println("Payment Mode : "+request.getPaymentMode());
        System.out.println("Original Amount : 25000");
        System.out.println("Coupon Applied : "+request.getCouponCode());
        System.out.println("Final Amount : "+response.getFinalAmount());
        System.out.println("Transaction ID : "+response.getTransactionId());
        System.out.println("Payment Status : "+response.getPaymentStatus());
    }
}
