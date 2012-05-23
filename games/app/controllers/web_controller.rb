class WebController < ApplicationController
  before_filter :server_info

  def index
    @games = @server_info['games'].nil? ? [] : @server_info['games']
  end

  def game_controller
  end

  def server_info
    render :json => {
      :realtime_server => APP_CONFIG['realtime_host'],
      :games => @server_info['games']
    }
  end

  private

  def server_info
    @server_info = ServerWrapper.info
  end
end
