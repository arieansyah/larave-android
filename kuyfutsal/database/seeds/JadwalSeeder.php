<?php

use Illuminate\Database\Seeder;

class JadwalSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
      DB::table('jadwals')->insert(array(
        [
         'jam' => '07.00 - 08.00',
        ],
        [
         'jam' => '08.00 - 09.00',
        ],
        [
          'jam' => '09.00 - 10.00',
        ],
        [
         'jam' => '10.00 - 11.00',
        ],
        [
         'jam' => '11.00 - 12.00',
        ],
        [
         'jam' => '12.00 - 13.00',
        ],
        [
         'jam' => '13.00 - 14.00',
        ],
        [
         'jam' => '14.00 - 15.00',
        ],
        [
         'jam' => '16.00 - 17.00',
        ],
        [
         'jam' => '17.00 - 18.00',
        ],
        [
         'jam' => '18.00 - 19.00',
        ],
        [
         'jam' => '19.00 - 20.00',
        ],
        [
         'jam' => '20.00 - 21.00',
        ],
        [
         'jam' => '21.00 - 22.00',
        ],
        [
         'jam' => '22.00 - 23.00',
        ],
        [
         'jam' => '23.00 - 00.00',
        ]
      ));
    }
}
