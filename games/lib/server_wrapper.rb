class ServerWrapper
  include HTTParty
  base_uri APP_CONFIG['realtime_host']

  def self.info
    return get("/info")
  rescue
    return { :ok => false, 'games' => {} }
  end
end
