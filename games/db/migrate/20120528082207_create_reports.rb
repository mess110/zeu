class CreateReports < ActiveRecord::Migration
  def change
    create_table :reports do |t|
      t.text  :stacktrace
      t.timestamps
    end
  end
end
