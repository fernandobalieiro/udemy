defmodule Cards do
  @spec create_deck :: [<<_::24, _::_*16>>, ...]
  def create_deck do
    values = ["Ace", "Two", "Three", "Four", "Five", "Six"]
    suits = ["Spades", "Clubs", "Hearts", "Diamonds"]

    for suit <- suits, value <- values do
        "#{value} of #{suit}"
    end
  end

  @spec shuffle(any) :: [any]
  def shuffle(deck) do
    Enum.shuffle(deck)
  end

  @spec contains?(any, any) :: boolean
  def contains?(deck, card) do
    Enum.member?(deck, card)
  end

  def deal(deck, hand_size) do
    Enum.split(deck, hand_size)
    # { hand, _ } = Enum.split(deck, hand_size)
    # hand
  end

  def save(deck, filename) do
    binary = :erlang.term_to_binary(deck)
    File.write(filename, binary)
  end

  def load(filename) do
    {status, binary} = File.read(filename)

    case status do
      :ok -> :erlang.binary_to_term(binary)
      :error -> "That file does not exist"
    end
  end
end
