defmodule Identicon do
  @moduledoc """
  Documentation for `Identicon`.
  """



  def main(input) do
    input
    |> hash_input
  end

  def hash_input(input) do
    :crypto.hash(:md5, input)
    |> :binary.bin_to_list
    |> generate_color
  end

  def generate_color(input_list) do
    Enum.take(input_list, 3)
  end
end
