# 💳 Razorpay Payment Gateway Integration in Kotlin Android App

This repository demonstrates how to integrate the **Razorpay Payment Gateway** into a modern **Kotlin-based Android app**. Payment integration can be tricky, but this guide simplifies the entire process with **step-by-step instructions** and working code.

🔗 **Tutorial Link:** [How to integrate Razorpay in Android (Blog Post)](https://www.boltuix.com/2022/12/how-to-integrate-razorpay-payment.html)  
📸 **Live Screenshot:**

<img src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgaEeeQrdy-E4r9XUpYBqL1MeDcas2Pfpj7deTPrnw3oIrfuea4v5FYz-UAZiXcYrI2eiKh-XTSde9D95iX09m1s_85TcSkxUNh51lp6ti6DpevCnYAcs8K8SXBQAjv9wwROYyBRNJRotedPRKauUvOnd4pyxdr0jupaVrNbfjmIGxSQm52bpYHzyLR/s16000/razerpay.jpg" width="100%"/>

---

## 📦 Features

- Simple integration with **minimal code**
- Based on **Razorpay SDK v1.6.26**
- Handles **checkout**, **success**, and **error** callbacks
- Test mode support (`rzp_test_...`)
- Custom UI styling for payment button
- Built using **Jetpack**, **Kotlin**, and **Material Components**

---

## 🚀 Getting Started

### 1. Add Razorpay Dependency

```gradle
// In build.gradle (app)
implementation 'com.razorpay:checkout:1.6.26'
```

### 2. Add Internet Permission

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

### 3. Add Payment Button to Layout

```xml
<com.google.android.material.button.MaterialButton
    android:id="@+id/fabBuyNow"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:text="Buy Now"
    app:icon="@drawable/ic_baseline_payments_24"
    android:background="@drawable/gradient_payment"
    android:textColor="@color/white"
    app:elevation="20dp"
/>
```

---

## 🧠 Implementation

In your Activity or Fragment:

```kotlin
val checkout = Checkout()
checkout.setKeyID("rzp_test_XXXXXXXX")

val options = JSONObject()
options.put("name", "Shopping Cart")
options.put("description", "Quality products at affordable price.")
options.put("currency", "INR")
options.put("amount", 50000) // Amount in paise = ₹500.00

val prefill = JSONObject()
prefill.put("email", "test@example.com")
prefill.put("contact", "9876543210")
options.put("prefill", prefill)

checkout.open(requireActivity(), options)
```

Handle result:

```kotlin
override fun onPaymentSuccess(razorpayPaymentID: String?) {
    Toast.makeText(context, "Payment Successful!", Toast.LENGTH_SHORT).show()
}

override fun onPaymentError(code: Int, response: String?) {
    Toast.makeText(context, "Payment Failed: $response", Toast.LENGTH_LONG).show()
}
```

---

## 📚 Resources

- [Official Razorpay Android Docs](https://razorpay.com/docs/payments/payment-gateway/android-integration/standard/)
- [Razorpay GitHub SDK](https://github.com/razorpay/razorpay-android-sample-app)

---

## 🧑‍💻 Author

Developed by [Bolt UIX](https://www.boltuix.com/)  
If you find this helpful, consider ⭐ starring this repo!

---

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
