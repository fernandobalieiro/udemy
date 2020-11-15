-module(cars).

%% API
-export([listPrices/1]).

listPrices(Currency) ->
  CarsMap = #{"BMW i8" => 150000, "Lamborghini Huracan" => 500000, "Ferrari f12" => 700000},
  ListPriceFun = fun(K, V, _) when is_list(K) -> listPriceForCarInCurrency(Currency, K, V) end,
  maps:fold(ListPriceFun, #{}, CarsMap).

listPriceForCarInCurrency(Currency, CarName, PriceInUSD) ->
  ConvertedPrice = convert(Currency, PriceInUSD),
  io:fwrite(CarName ++ " => Price: " ++ integer_to_list(ConvertedPrice) ++ "\n").

convert(Currency, Value) ->
  case Currency of
    eur -> round(Value * 0.84);
    gbp -> round(Value * 0.76);
    usd -> Value;
    _ -> 0
  end.
