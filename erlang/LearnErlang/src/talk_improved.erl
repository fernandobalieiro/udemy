-module(talk_improved).

%% API
-export([run/0, alice/0, bob/1]).

alice() ->
  receive
    message ->
      io:fwrite("Alice got a message\n"),
      bob ! message,
      alice();
    finished -> io:fwrite("Alice is finished\n")
  end.

bob(0) ->
  alice ! finished,
  io:fwrite("Bob is finished\n");

bob(N) ->
  alice ! message,
  receive
    message -> io:fwrite("Bob got a message\n")
  end,
  bob(N - 1).

run() ->
  register(alice, spawn(talk_improved, alice, [])),
  register(bob, spawn(talk_improved, bob, [3])).
