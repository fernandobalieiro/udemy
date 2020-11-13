-module(hw).

%% API
-export([helloworld/0, helloworld/1]).

helloworld() ->
  "Hello world!".

helloworld(Name) ->
  "Hello world!" ++ Name.
