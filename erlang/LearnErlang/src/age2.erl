-module(age2).

%% API
-export([getType/1]).

getType(Age) when Age < 13 ->
  child;
getType(Age) when Age < 18 ->
  teen;
getType(Age) when Age > 17 ->
  adult.
