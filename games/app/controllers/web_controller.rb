class WebController < ApplicationController
  def index
    @server_info = ServerWrapper.info
    @games = @server_info['games'].nil? ? [] : @server_info['games']
  end

  def game_controller
  end

  def server_info
    @server_info = ServerWrapper.info
    render :json => {
      :realtime_server => APP_CONFIG['realtime_host'],
      :games => @server_info['games']
    }
  end
end
