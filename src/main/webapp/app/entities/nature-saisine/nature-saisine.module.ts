import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MjkSharedModule } from 'app/shared/shared.module';
import { NatureSaisineComponent } from './nature-saisine.component';
import { NatureSaisineDetailComponent } from './nature-saisine-detail.component';
import { NatureSaisineUpdateComponent } from './nature-saisine-update.component';
import { NatureSaisineDeleteDialogComponent } from './nature-saisine-delete-dialog.component';
import { natureSaisineRoute } from './nature-saisine.route';

@NgModule({
  imports: [MjkSharedModule, RouterModule.forChild(natureSaisineRoute)],
  declarations: [NatureSaisineComponent, NatureSaisineDetailComponent, NatureSaisineUpdateComponent, NatureSaisineDeleteDialogComponent],
  entryComponents: [NatureSaisineDeleteDialogComponent],
})
export class MjkNatureSaisineModule {}
