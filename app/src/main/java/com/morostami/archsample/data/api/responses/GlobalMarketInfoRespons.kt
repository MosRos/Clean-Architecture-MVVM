/*
 * *
 *  * Created by Moslem Rostami on 7/25/20 6:02 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/25/20 6:02 PM
 *
 */

package com.morostami.archsample.data.api.responses


import com.google.gson.annotations.SerializedName
import com.morostami.archsample.domain.model.GlobalMarketInfo

data class GlobalMarketInfoRespons(
 @SerializedName("data")
 var `data`: Data? = null
) {
 fun mapToGlobalInfo() = GlobalMarketInfo(
  activeCryptocurrencies = data?.activeCryptocurrencies ?: 0,
  markets = data?.markets ?: 0,
  endedIcos = data?.endedIcos ?: 0,
  ongoingIcos = data?.ongoingIcos ?: 0,
  upcomingIcos = data?.upcomingIcos ?: 0,
  marketCapChangePercentage24hUsd = data?.marketCapChangePercentage24hUsd ?: 0.0,
  marketCapPercentage = data?.marketCapPercentage,
  totalMarketCapUsd = data?.totalMarketCap?.usd ?: 0.0,
  totalVolumeUsd = data?.totalVolume?.usd ?: 0.0,
  updatedAt = data?.updatedAt ?: 0
 )
}

data class Data(
 @SerializedName("active_cryptocurrencies")
 var activeCryptocurrencies: Int? = null, // 7704
 @SerializedName("ended_icos")
 var endedIcos: Int? = null, // 3370
 @SerializedName("market_cap_change_percentage_24h_usd")
 var marketCapChangePercentage24hUsd: Double? = null, // 1.6163610157482386
 @SerializedName("market_cap_percentage")
 var marketCapPercentage: Map<String, Double>? = null,
 @SerializedName("markets")
 var markets: Int? = null, // 505
 @SerializedName("ongoing_icos")
 var ongoingIcos: Int? = null, // 55
 @SerializedName("total_market_cap")
 var totalMarketCap: TotalMarketCap? = null,
 @SerializedName("total_volume")
 var totalVolume: TotalVolume? = null,
 @SerializedName("upcoming_icos")
 var upcomingIcos: Int? = null, // 0
 @SerializedName("updated_at")
 var updatedAt: Long? = null // 1595683799
)

data class MarketCapPercentage(
 @SerializedName("ada")
 var ada: Double? = null, // 1.4052025723150396
 @SerializedName("bch")
 var bch: Double? = null, // 1.519691948189148
 @SerializedName("bnb")
 var bnb: Double? = null, // 0.9868778749130445
 @SerializedName("bsv")
 var bsv: Double? = null, // 1.1568385366396308
 @SerializedName("btc")
 var btc: Double? = null, // 59.96100968373725
 @SerializedName("eth")
 var eth: Double? = null, // 10.800972099457915
 @SerializedName("link")
 var link: Double? = null, // 0.9838516103795665
 @SerializedName("ltc")
 var ltc: Double? = null, // 1.043386925175997
 @SerializedName("usdt")
 var usdt: Double? = null, // 3.4614082994100146
 @SerializedName("xrp")
 var xrp: Double? = null // 3.153280591504417
)

data class TotalMarketCap(
 @SerializedName("aed")
 var aed: Double? = null, // 1083427243670.3086
 @SerializedName("ars")
 var ars: Double? = null, // 21190603908821.64
 @SerializedName("aud")
 var aud: Double? = null, // 415159408760.19794
 @SerializedName("bch")
 var bch: Double? = null, // 1218592781.0381775
 @SerializedName("bdt")
 var bdt: Double? = null, // 25004350988267.96
 @SerializedName("bhd")
 var bhd: Double? = null, // 111232846919.02698
 @SerializedName("bmd")
 var bmd: Double? = null, // 294970662583.8032
 @SerializedName("bnb")
 var bnb: Double? = null, // 14987298121.914778
 @SerializedName("brl")
 var brl: Double? = null, // 1542858799177.7104
 @SerializedName("btc")
 var btc: Double? = null, // 30756798.621758197
 @SerializedName("cad")
 var cad: Double? = null, // 395716417535.9875
 @SerializedName("chf")
 var chf: Double? = null, // 271578604128.91974
 @SerializedName("clp")
 var clp: Double? = null, // 228366405550586.84
 @SerializedName("cny")
 var cny: Double? = null, // 2069897630549.3228
 @SerializedName("czk")
 var czk: Double? = null, // 6644332162965.186
 @SerializedName("dkk")
 var dkk: Double? = null, // 1883122207001.2578
 @SerializedName("eos")
 var eos: Double? = null, // 111194013146.08437
 @SerializedName("eth")
 var eth: Double? = null, // 1036754989.7779509
 @SerializedName("eur")
 var eur: Double? = null, // 253062410726.54654
 @SerializedName("gbp")
 var gbp: Double? = null, // 230598919917.47726
 @SerializedName("hkd")
 var hkd: Double? = null, // 2286450342485.219
 @SerializedName("huf")
 var huf: Double? = null, // 87553929494780.98
 @SerializedName("idr")
 var idr: Double? = null, // 4307382843045635.5
 @SerializedName("ils")
 var ils: Double? = null, // 1006652279612.9978
 @SerializedName("inr")
 var inr: Double? = null, // 22041594270375.914
 @SerializedName("jpy")
 var jpy: Double? = null, // 31308913424008.902
 @SerializedName("krw")
 var krw: Double? = null, // 354433798454071.6
 @SerializedName("kwd")
 var kwd: Double? = null, // 90396414284.76968
 @SerializedName("lkr")
 var lkr: Double? = null, // 54770711008024.914
 @SerializedName("ltc")
 var ltc: Double? = null, // 6240016557.079336
 @SerializedName("mmk")
 var mmk: Double? = null, // 403002626508683.5
 @SerializedName("mxn")
 var mxn: Double? = null, // 6573598198077.611
 @SerializedName("myr")
 var myr: Double? = null, // 1257312449263.461
 @SerializedName("nok")
 var nok: Double? = null, // 2702019760466.4106
 @SerializedName("nzd")
 var nzd: Double? = null, // 443798700331.7856
 @SerializedName("php")
 var php: Double? = null, // 14552345781740.375
 @SerializedName("pkr")
 var pkr: Double? = null, // 49392837449657.67
 @SerializedName("pln")
 var pln: Double? = null, // 1114753128036.71
 @SerializedName("rub")
 var rub: Double? = null, // 21140576884447.434
 @SerializedName("sar")
 var sar: Double? = null, // 1106353543448.9727
 @SerializedName("sek")
 var sek: Double? = null, // 2606471978530.28
 @SerializedName("sgd")
 var sgd: Double? = null, // 407870683687.75446
 @SerializedName("thb")
 var thb: Double? = null, // 9329033900860.664
 @SerializedName("try")
 var tryX: Double? = null, // 2019456172394.177
 @SerializedName("twd")
 var twd: Double? = null, // 8691163087700.464
 @SerializedName("uah")
 var uah: Double? = null, // 8200177045563.163
 @SerializedName("usd")
 var usd: Double? = null, // 294970662583.8032
 @SerializedName("vef")
 var vef: Double? = null, // 7.329656447571486e+16
 @SerializedName("vnd")
 var vnd: Double? = null, // 6.794725120663195e+15
 @SerializedName("xag")
 var xag: Double? = null, // 12957214641.504848
 @SerializedName("xau")
 var xau: Double? = null, // 155116222.3329445
 @SerializedName("xdr")
 var xdr: Double? = null, // 211203123999.2792
 @SerializedName("xlm")
 var xlm: Double? = null, // 3021373932756.3223
 @SerializedName("xrp")
 var xrp: Double? = null, // 1422571117222.209
 @SerializedName("zar")
 var zar: Double? = null // 4915839476703.617
)

data class TotalVolume(
 @SerializedName("aed")
 var aed: Double? = null, // 211386538775.86362
 @SerializedName("ars")
 var ars: Double? = null, // 4134480133323.2817
 @SerializedName("aud")
 var aud: Double? = null, // 81001387929.615
 @SerializedName("bch")
 var bch: Double? = null, // 237758568.16030106
 @SerializedName("bdt")
 var bdt: Double? = null, // 4878576979328.05
 @SerializedName("bhd")
 var bhd: Double? = null, // 21702543152.545776
 @SerializedName("bmd")
 var bmd: Double? = null, // 57551467132.00756
 @SerializedName("bnb")
 var bnb: Double? = null, // 2924158584.8081517
 @SerializedName("brl")
 var brl: Double? = null, // 301025826407.32184
 @SerializedName("btc")
 var btc: Double? = null, // 6000931.989170261
 @SerializedName("cad")
 var cad: Double? = null, // 77207882973.60892
 @SerializedName("chf")
 var chf: Double? = null, // 52987463134.03793
 @SerializedName("clp")
 var clp: Double? = null, // 44556368989290.04
 @SerializedName("cny")
 var cny: Double? = null, // 403855910305.4367
 @SerializedName("czk")
 var czk: Double? = null, // 1296369817735.32
 @SerializedName("dkk")
 var dkk: Double? = null, // 367414321317.44934
 @SerializedName("eos")
 var eos: Double? = null, // 21694966329.184628
 @SerializedName("eth")
 var eth: Double? = null, // 202280356.27508828
 @SerializedName("eur")
 var eur: Double? = null, // 49374784887.7604
 @SerializedName("gbp")
 var gbp: Double? = null, // 44991952908.32241
 @SerializedName("hkd")
 var hkd: Double? = null, // 446107319900.39954
 @SerializedName("huf")
 var huf: Double? = null, // 17082570352790.324
 @SerializedName("idr")
 var idr: Double? = null, // 840409686661923.9
 @SerializedName("ils")
 var ils: Double? = null, // 196407042910.74496
 @SerializedName("inr")
 var inr: Double? = null, // 4300516115999.1235
 @SerializedName("jpy")
 var jpy: Double? = null, // 6108654623741.754
 @SerializedName("krw")
 var krw: Double? = null, // 69153267391148.86
 @SerializedName("kwd")
 var kwd: Double? = null, // 17637165065.807888
 @SerializedName("lkr")
 var lkr: Double? = null, // 10686265361998.455
 @SerializedName("ltc")
 var ltc: Double? = null, // 1217484154.6687894
 @SerializedName("mmk")
 var mmk: Double? = null, // 78629488812426.6
 @SerializedName("mxn")
 var mxn: Double? = null, // 1282568975917.0688
 @SerializedName("myr")
 var myr: Double? = null, // 245313128650.18213
 @SerializedName("nok")
 var nok: Double? = null, // 527188704369.32837
 @SerializedName("nzd")
 var nzd: Double? = null, // 86589174976.39609
 @SerializedName("php")
 var php: Double? = null, // 2839295415399.143
 @SerializedName("pkr")
 var pkr: Double? = null, // 9636993171254.629
 @SerializedName("pln")
 var pln: Double? = null, // 217498504585.28308
 @SerializedName("rub")
 var rub: Double? = null, // 4124719404497.694
 @SerializedName("sar")
 var sar: Double? = null, // 215859669007.23187
 @SerializedName("sek")
 var sek: Double? = null, // 508546460481.5276
 @SerializedName("sgd")
 var sgd: Double? = null, // 79579291176.78355
 @SerializedName("thb")
 var thb: Double? = null, // 1820179617917.866
 @SerializedName("try")
 var tryX: Double? = null, // 394014321668.5273
 @SerializedName("twd")
 var twd: Double? = null, // 1695725203311.0352
 @SerializedName("uah")
 var uah: Double? = null, // 1599929347483.1313
 @SerializedName("usd")
 var usd: Double? = null, // 57551467132.00756
 @SerializedName("vef")
 var vef: Double? = null, // 1.430082837514297e+16
 @SerializedName("vnd")
 var vnd: Double? = null, // 1325712855737898.8
 @SerializedName("xag")
 var xag: Double? = null, // 2528070778.398421
 @SerializedName("xau")
 var xau: Double? = null, // 30264590.020708792
 @SerializedName("xdr")
 var xdr: Double? = null, // 41207656187.057236
 @SerializedName("xlm")
 var xlm: Double? = null, // 589497616682.9069
 @SerializedName("xrp")
 var xrp: Double? = null, // 277556602336.6717
 @SerializedName("zar")
 var zar: Double? = null // 959125126517.8131
)