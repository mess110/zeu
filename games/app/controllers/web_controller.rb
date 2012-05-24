class WebController < ApplicationController
  before_filter :server_info

  def index
    @games = @server_info['games'].nil? ? [] : @server_info['games']
  end

  def game_controller
  end
end
