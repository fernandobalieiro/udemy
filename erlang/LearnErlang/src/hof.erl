-module(hof).

%% API
-export([double/0]).

double() ->
  F = fun(X) -> 2 * X end,
  map(F, [1, 2, 3, 4, 5]).

map(_, []) ->
  [];
map(F, [First | Rest]) ->
  [F(First) | map(F, Rest)].
