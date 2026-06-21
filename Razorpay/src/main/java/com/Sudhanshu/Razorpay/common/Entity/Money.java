package com.Sudhanshu.Razorpay.common.Entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode


@Embeddable
public class Money {
    private int amountunits;
    private String currency;
    public  Money(int amountunits,String currency)
    {
        this.amountunits=amountunits;
        this.currency=currency;
    }
    public  Money of(int amountunits,String Currency)
    {
        return new Money(amountunits,Currency);
    }
    public Money inr(int amountunits)
    {
        return new Money(amountunits,"INR");
    }
    public  Money add(Money other)
    {
        if(!this.currency.equals(other.currency)){
            throw new IllegalArgumentException("Cannot add money with different curencies");
    }
        return  new Money(this.amountunits+other.amountunits,this.currency);
    }
    public  Money Subtract(Money other)
    {
        if(!this.currency.equals(other.currency)){
            throw new IllegalArgumentException("Cannot subtract money with different curencies");
        }
        return  new Money(this.amountunits-other.amountunits,this.currency);
    }


}
